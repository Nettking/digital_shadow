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
port = 1883

for i, row in data.iterrows():
    current_temp = row['temp']
    message = f'{{ "temperature": {{ "id": 1, "txt": "temperature", "t":{current_temp} }} }}'
    establish_connection(host, port, topic, topic, message)
    time.sleep(1)