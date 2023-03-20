import pandas as pd
import time
import numpy as np
from mqtt import *
from sklearn.linear_model import LinearRegression

# Read the data
data = pd.read_csv('data.csv')

# Convert time column to datetime object
data['time'] = pd.to_datetime(data['time'], format='%Y-%m-%d %H:%M:%S:%f')
temp_list = []

host = '192.168.0.124'
topic = 'CPS2021/tempoutput'
topic_response = 'CPS2021/SwitchControl'
port = 1883

# Establish a single connection to the MQTT broker
client = establish_connection(host, port, topic_response, topic)

# Prepare the data for the linear regression model
data['switch'] = data['switch'].replace({'ON': 1, 'OFF': 0})
data['switch'] = data['switch'].astype(int)
X = data[['switch', 'temp']]
y = data['temp']

# Train the linear regression model
model = LinearRegression()
model.fit(X, y)

# Initialize temperature and heating state
current_temp = 20
heating_state = False

# Simulate the room temperature changes based on heating state
while True:
    # Update the current_temp based on the heating_state
    current_temp = model.predict([[int(heating_state), current_temp]])[0]

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
