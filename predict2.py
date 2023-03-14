import pandas as pd
import numpy as np
import datetime
import matplotlib
matplotlib.use('TkAgg') # or any other backend that supports showing figures
from sklearn.preprocessing import MinMaxScaler
from keras.models import Sequential
from keras.layers import Dense, LSTM

# Read the data
data = pd.read_csv('data.csv')

# Convert time column to datetime object
data['time'] = pd.to_datetime(data['time'], format='%Y-%m-%d %H:%M:%S:%f')

# Normalize the temperature data
scaler = MinMaxScaler(feature_range=(0, 1))
scaled_data = scaler.fit_transform(data[['temp']])

# Split data into training and testing sets
training_data_len = int(np.ceil(len(scaled_data) * 0.8))
train_data = scaled_data[0:training_data_len, :]
x_train = []
y_train = []

for i in range(60, len(train_data)):
    x_train.append(train_data[i-60:i, 0])
    y_train.append(train_data[i, 0])

# Convert the x_train and y_train to numpy arrays
x_train, y_train = np.array(x_train), np.array(y_train)

# Reshape the data
x_train = np.reshape(x_train, (x_train.shape[0], x_train.shape[1], 1))

# Build LSTM model
model = Sequential()
model.add(LSTM(units=50, return_sequences=True, input_shape=(x_train.shape[1], 1)))
model.add(LSTM(units=50, return_sequences=False))
model.add(Dense(units=25))
model.add(Dense(units=1))

# Compile the model
model.compile(optimizer='adam', loss='mean_squared_error')

# Train the model
model.fit(x_train, y_train, batch_size=1, epochs=1)

# Prepare testing data
test_data = scaled_data[training_data_len - 60:, :]
x_test = []
y_test = data['temp'][training_data_len:]

for i in range(60, len(test_data)):
    x_test.append(test_data[i-60:i, 0])

# Convert the data to a numpy array
x_test = np.array(x_test)

# Reshape the data
x_test = np.reshape(x_test, (x_test.shape[0], x_test.shape[1], 1))

# Get the model's predicted temperature values
predictions = model.predict(x_test)
predictions = scaler.inverse_transform(predictions)

# Append predicted temperatures to dataframe
pred_data = pd.DataFrame({'time': data['time'][training_data_len:].reset_index(drop=True),
                          'temp': predictions.reshape(-1)})

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
