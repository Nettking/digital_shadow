import pandas as pd
from mqtt import *
import time

# Read the data
data = pd.read_csv('output.csv')

# Convert time column to datetime object
data['time'] = pd.to_datetime(data['time'], format='%Y-%m-%d %H:%M:%S.%f')
temp_list = []

host = '192.168.0.124'
topic = 'CPS2021/tempoutput'
topic_response = 'CPS2021/SwitchControl'
port = 1883

# Establish a single connection to the MQTT broker
client = establish_connection(host, port, topic_response, topic)
last_temp = 17.8
current_temp = 17.9
mem = [17.8, 17.9]

def predict_temp(curr_temp, prev_temp, mem):

    # Compute new temperature based on previous temperature and switch
    if switchState == True:
        print("Switch On")
        new_temp = curr_temp + 0.1 * (20 - curr_temp) + 0.1 * (prev_temp - curr_temp)
    else:
        print("Switch Off")
        new_temp = curr_temp - 0.1 * (curr_temp - mem[-1]) + 0.1 * (prev_temp - curr_temp)

    # Append new temperature to memory
    mem.append(new_temp)

    # Return list of predicted temperatures
    return new_temp

# Publish temperature data for each row in the CSV file
while(True):
    
    new_temp = predict_temp(current_temp, last_temp, mem)
    
    message = f'{{ "temperature": {{ "id": 1, "txt": "temperature", "t":{new_temp} }} }}'
    client.publish(topic, message, retain=False, qos=1)
    print("Published message: " + message)

    last_temp = current_temp

    current_temp = new_temp

    # Clear the message by publishing an empty payload with retain=True
    client.publish(topic, payload=None, retain=True)

    client.loop(timeout=1.0)
    time.sleep(1)

