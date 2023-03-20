import pandas as pd
import time
import numpy as np
from mqtt import *

host = '192.168.0.124'
topic = 'CPS2021/tempoutput'
topic_response = 'CPS2021/SwitchControl'
port = 1883

# Establish a single connection to the MQTT broker
client = establish_connection(host, port, topic_response, topic)

# Initialize temperature and heating state
current_temp = 20
heating_state = False

# Define temperature change parameters
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
    message = f'{{ "temperature": {{ "id": 1, "txt": "temperature", "t":{current_temp} }} }}'
    client.publish(topic, message, retain=False, qos=1)
    print("Published message: " + message)

    # Clear the message by publishing an empty payload with retain=True
    client.publish(topic, payload=None, retain=True)

    # Update heating_state based on switchState
    heating_state = switchState

    client.loop(timeout=1.0)
    time.sleep(1)
