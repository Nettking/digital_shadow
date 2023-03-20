import pandas as pd
import time
import numpy as np
from mqtt import *
import paho.mqtt.client as mqtt

switchState = False

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
    
    # Update the heating state based on the new switch state
    global heating_state
    heating_state = switchState

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

host = '192.168.0.124'
topic_temp = 'CPS2021/tempoutput'
topic_switch = 'CPS2021/SwitchControl'
port = 1883

client = establish_connection(host, port, topic_switch, topic_temp, topic_temp)

data = pd.read_csv('data.csv')
data['time'] = pd.to_datetime(data['time'], format='%Y-%m-%d %H:%M:%S:%f')
temp_list = []

current_temp = 19
heating_state = False  

heating_rate = 0.025
cooling_rate = 0.025
time_interval = 1

# Simulate the room temperature changes based on heating state
while True:
    # Update the current_temp based on the heating_state
    if heating_state:
        current_temp += heating_rate * time_interval
    else:
        current_temp -= cooling_rate * time_interval

    # Publish the current temperature
    message = f'{{"temperature":{{"id":1,"txt":"temperature","t":{current_temp}}}}}'
    print(message)
    client.publish(topic_temp, message)#, retain=False, qos=1)
    print("Published message: " + message)

    # Update heating_state based on switchState
    client.loop(timeout=1.0)
    time.sleep(1)