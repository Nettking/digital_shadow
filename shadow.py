import pandas as pd
import time
import numpy as np
import matplotlib.pyplot as plt
from mqtt import *
import paho.mqtt.client as mqtt

import datetime
import matplotlib

matplotlib.use('TkAgg') # or any other backend that supports showing figures

print("Starting Martin & Benjy's digital shadow of Oysteins Room!")
host = input("Please enter host (openhabian) IP: ")
topic_temp = 'CPS2021/tempoutput'
topic_switch = 'CPS2021/SwitchControl'
port = input("Please enter host port (should be 1883): ")
data = pd.read_csv('output_data.csv')
data['time'] = pd.to_datetime(data['time'], format='%Y-%m-%d %H:%M:%S:%f')
temp_list = []

def on_message(client, userdata, message):
    # Decode the message payload from bytes to string
    payload = message.payload.decode()
    global switchState
    if payload == str('{"SwitchOff":{"did":1}}'):
        print('Slår av...')
        switchState = False
    elif payload == str('{"SwitchOn":{"did":1}}'):
        print('Slår på...')
        switchState = True
    else:
        print('False message:', payload, "on topic:", message.topic)
    
    print('Received payload:', payload, "on topic:", message.topic)
    

# Function to establish a connection to the MQTT broker
def establish_connection(MQTT_BROKER_ADDR, MQTT_BROKER_PORT, MQTT_TOPIC_SUB, MQTT_TOPIC_PUB, message="Hello, world!"):

    # Set up the MQTT client and connect to the broker
    client = mqtt.Client()
    client.connect(MQTT_BROKER_ADDR, MQTT_BROKER_PORT)

    # Subscribe to the specified topic with QoS level 1
    client.subscribe(MQTT_TOPIC_SUB, qos=1)

    # Set up the callback function for receiving messages
    client.on_message = on_message

    # Publish a message to the specified topic with the retain flag set to False and QoS level 1
    client.publish(MQTT_TOPIC_PUB, message, retain=False, qos=1)
    print("Published message: " + message)

    return client

client = establish_connection(host, port, topic_switch, topic_temp)

def update_temperature(current_temp, rate, time_interval, direction):
    return current_temp + direction * rate * time_interval

# Simulate the room temperature changes based on heating state
current_temp = 20.5
heating_rate = 0.025
cooling_rate = 0.025
time_interval = 1
switchState = True
last_state = True
delay = False
num_iterations = 1591
count = 0
direction = 1
delay_counter = 0
heating_delay = 4
cooling_delay = 16
transition_delay = 4
transition_delay_counter = 0
delay_with_no_temp_change = False
delay_with_temp_change = False


for i in range(num_iterations):
    if delay_with_no_temp_change:
        if transition_delay_counter < transition_delay:
            print('im in transition delay')
            transition_delay_counter += 1
        else:
            transition_delay_counter = 0
            
            delay_with_no_temp_change = False
    else:
        
        if last_state!=switchState:
            delay_with_temp_change = True
        last_state = switchState
        if switchState:
            if delay_with_temp_change:
                if delay_counter < heating_delay:
                    current_temp = update_temperature(current_temp, cooling_rate, time_interval, -1)
                    if(delay_counter == heating_delay-1):
                        delay_with_no_temp_change = True
                    else:
                        delay_with_no_temp_change = False
                    delay_counter += 1
                else:
                    delay_with_temp_change = False
                    delay_counter = 0
            else:
                delay_counter = 0
                current_temp = update_temperature(current_temp, heating_rate, time_interval, 1)
        else:
            if delay_with_temp_change:
                if delay_counter < cooling_delay:
                    current_temp = update_temperature(current_temp, heating_rate, time_interval, 1)
                    if(delay_counter == cooling_delay-1):
                        delay_with_no_temp_change = True
                    else:
                        delay_with_no_temp_change = False
                    delay_counter += 1
                else:
                    delay_with_temp_change = False
                    delay_counter = 0
            else:
                delay_counter = 0
                current_temp = update_temperature(current_temp, cooling_rate, time_interval, -1)


    current_temp = np.round(current_temp, 2)
    
    # Add current temperature to the list
    temp_list.append(current_temp)
    

    # Publish the current temperature
    message = f'{{"temperature":{{"id":1,"txt":"temperature","t":{current_temp}}}}}'
    print(message)
    client.publish(topic_temp, message)#, retain=False, qos=1)
    print("Published message: " + message)



    # Update heating_state based on switchState
    client.loop(timeout=0.01)
    time.sleep(0.1)



# Plot the temperature over time
time_list = np.arange(num_iterations)
plt.plot(time_list, temp_list)
plt.xlabel('Time')
plt.ylabel('Temperature')


# Define function to predict temperature recursively
def predict_temp(data, idx, prev_temp, prev_switch, mem):
    if idx == len(data):
        return []
    # Get current temperature and switch
    curr_temp = data.loc[idx, 'temp']
    curr_switch = data.loc[idx, 'switch']

    # Compute new temperature based on previous temperature and switch
    if curr_switch == 'ON':
        new_temp = curr_temp + 0.1 * (20 - curr_temp) + 0.1 * (prev_temp - curr_temp)
    else:
        new_temp = curr_temp - 0.1 * (curr_temp - mem[-1]) + 0.1 * (prev_temp - curr_temp)

    # Append new temperature to memory
    mem.append(new_temp)

    # Predict next temperature recursively
    next_temps = predict_temp(data, idx+1, new_temp, curr_switch, mem)

    # Return list of predicted temperatures
    return [new_temp] + next_temps

# Set initial values for memory and previous temperature
memory = [data.loc[0, 'temp']]
prev_temp = data.loc[0, 'temp']

# Predict temperatures recursively
predicted_temps = predict_temp(data, 1, prev_temp, data.loc[0, 'switch'], memory)

# Append predicted temperatures to dataframe
pred_data = pd.DataFrame({'time': [data.loc[0, 'time'] + datetime.timedelta(seconds=i*60) for i in range(len(predicted_temps))],
                        'temp': predicted_temps})

# Combine original data and predicted data
combined_data = pd.concat([data[['time', 'temp']], pred_data])

# Plot the results
plt.plot(time_list, combined_data['temp'], color='blue')
plt.show()

