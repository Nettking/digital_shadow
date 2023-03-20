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
last_temp = 20

# Publish temperature data for each row in the CSV file
for i, row in data.iterrows():
    current_temp = row['temp']
    
    if switchState == True:
        print('On')    
    elif switchState == False:
        print('Off')
    last_temp = current_temp

    message = f'{{ "temperature": {{ "id": 1, "txt": "temperature", "t":{current_temp} }} }}'
    client.publish(topic, message, retain=False, qos=1)
    print("Published message: " + message)

    # Clear the message by publishing an empty payload with retain=True
    client.publish(topic, payload=None, retain=True)

    client.loop(timeout=1.0)
    time.sleep(1)
