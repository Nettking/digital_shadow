import pandas as pd
import numpy as np
import datetime
import matplotlib
matplotlib.use('TkAgg') # or any other backend that supports showing figures

# Read the data
data = pd.read_csv('data.csv')

# Convert time column to datetime object
data['time'] = pd.to_datetime(data['time'], format='%Y-%m-%d %H:%M:%S:%f')

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
import matplotlib.pyplot as plt


# Open a file for writing
with open('output.csv', 'w') as f:
    f.write('time,temp\n')  # write header row
    for i, row in pred_data.iterrows():
        f.write(f"{row['time']},{row['temp']}\n")

plt.plot(combined_data['time'], combined_data['temp'], color='blue')
plt.plot(pred_data['time'], pred_data['temp'], color='green')
plt.show()
