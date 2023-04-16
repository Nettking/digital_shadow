# digital_shadow
A simple digital shadow of Øystein's room. 
This repo is using Tkinter is a built-in GUI module for Python. It allows you to create desktop applications with graphical user interfaces. 
To install tkinter, you can try running the following command in your terminal or command prompt:<\br>
```sh
sudo apt-get install python3-tk
```
Install requirements: <\br>
```sh
pip install -r requirements.txt
```

<strong>This code may be separated into the following sections:</strong>
<ul>
 	<li>Importing necessary library files</li>
 	<li>Global variables and functions are defined.</li>
 	<li>Creating a connection using a MQTT broker.</li>
 	<li>The input data is read from a CSV file.</li>
 	<li>Changing the temperature of the room depending on the heating condition and MQTT messages.</li>
 	<li>Graphing the temperature simulation over time.</li>
 	<li>Reading and processing temperature forecast input data.</li>
 	<li>Predicting temperatures recursively based on input data.</li>
 	<li>Mixing the original and anticipated temperatures and plotting them.</li>
</ul>
<strong>Let's examine each component in further depth:</strong>
<ul>
 	<li>The code imports libraries needed for data handling, graphing, and MQTT communication.</li>
 	<li>Global variables such as switchState, heating state, and data are defined, along with global functions. The on message method is created to handle incoming MQTT messages, while the establish connection function configures a MQTT client, establishes a connection to the broker, and subscribes to a topic.</li>
 	<li>In order to create a connection to a MQTT broker, the establish connection function is used with the required arguments.</li>
 	<li>Receiving data from a CSV file as input: The input data are taken from the output data.csv CSV file, and the time column is transformed to datetime objects.</li>
 	<li>Changing room temperature depending on heating state and MQTT messages: simulation The code replicates variations in room temperature by increasing or decreasing the current temperature dependent on the heating state, which is updated by receiving MQTT messages. The simulation runs for a certain number of iterations (num iterations), and at each step, the current temperature is communicated to the MQTT broker.</li>
 	<li>Graphing the simulated temperature over time Using matplotlib.pyplot, the simulated temperatures are graphed over time.</li>
 	<li>Reading and processing temperature forecast input data: The data is received from the file output data.csv, and the time column is transformed to datetime objects.</li>
 	<li>The predict temp function is used to iteratively predict temperatures based on input data, taking the previous temperature and switch state into consideration.</li>
 	<li>Original and forecasted temperatures are merged and plotted using matplotlib.pyplot.</li>
 	<li>The objective of the code is to simulate and forecast changes in room temperature depending on the heating status, as controlled by MQTT messages. The simulated and forecasted temperatures are then graphically shown.</li>
</ul>


### Tools
#### clean.py
The clean.py script is not part of the digital_shadow, but it was used to prepare the data for further processing.

Here is a brief description of its functionality:

clean.py is a standalone Python script used to clean a CSV file by removing specified columns. It reads data from an input CSV file, filters out undesired columns, and writes the cleaned data to a new output CSV file.

Here's a step-by-step explanation of the script:
<ol>
 	<li>Define the input and output file names: input_data.csv and output_data.csv.</li>
 	<li>Specify the columns to remove by their indices in the columns_to_remove list.</li>
 	<li>Open both the input and output files using a with statement, which ensures proper handling of file opening and closing.</li>
 	<li>Create a CSV reader object to read data from the input file, and a CSV writer object to write data to the output file.</li>
 	<li>The delimiter for both is set to a semicolon (;).</li>
 	<li>Iterate over each row in the input file using a for loop.</li>
 	<li>Use a list comprehension to filter out the undesired columns by checking if the current index is not in the columns_to_remove list.</li>
 	<li>Write the filtered row to the output file using the CSV writer object.</li>
</ol>
This script is helpful when you need to clean up CSV files by removing unnecessary or unwanted columns before processing the data further.

#### MQTT.py

MQTT.py is a Python script that utilizes the Paho MQTT library to establish a connection to an MQTT broker, subscribe to a topic, and publish messages. It also contains a callback function to handle incoming messages.

Here's a detailed explanation of the script:
<ol>
 	<li>Import the paho.mqtt.client module and create an alias for it as mqtt.</li>
 	<li>Define a global variable switchState to represent the state of a switch (True or False).</li>
 	<li>Define the on_message function, which serves as a callback for handling incoming MQTT messages. This function:
<ol>
 	<li>Decodes the message payload from bytes to a string.</li>
 	<li>Updates the switchState variable based on the received payload.</li>
 	<li>Prints information about the received message.</li>
 	<li>Updates the heating_state variable based on the new switch state.</li>
</ol>
</li>
 	<li>Define the establish_connection function, which establishes a connection to an MQTT broker and sets up the MQTT client for subscribing and publishing messages. This function:
<ol>
 	<li>Creates an MQTT client instance and connects it to the specified MQTT broker.</li>
 	<li>Sets the on_message function as the callback for handling incoming messages.</li>
 	<li>Subscribes to the specified topic with QoS level 1.</li>
 	<li>Publishes a message to the specified topic with the retain flag set to False and QoS level 1.</li>
 	<li>Prints information about the published message.</li>
 	<li>Returns the MQTT client instance.</li>
</ol>
</li>
</ol>
The MQTT.py script can be used as a module in other Python applications that require MQTT functionality for communication with IoT devices, sensors, or other MQTT-enabled systems.

#### predict_lstm.py 

The predict_lstm.py script uses an LSTM (Long Short-Term Memory) neural network model to predict temperature values based on historical data. The script reads temperature data from a CSV file, preprocesses and splits the data, trains an LSTM model, and then predicts the temperature values for a test dataset. It writes the predicted values to an output CSV file and visualizes the results using a plot.

Here's a detailed explanation of the script:
<ol>
 	<li>Import necessary libraries: pandas, numpy, matplotlib, MinMaxScaler, and Keras components (Sequential, Dense, LSTM).</li>
 	<li>Specify the matplotlib backend to use (e.g., 'TkAgg').</li>
 	<li>Read temperature data from a CSV file named 'data.csv' using pandas.</li>
 	<li>Convert the 'time' column to a datetime object.</li>
 	<li>Normalize the temperature data using MinMaxScaler from scikit-learn.</li>
 	<li>Split the data into training and testing datasets (80% training and 20% testing).</li>
 	<li>Create the input and output sequences for the LSTM model using a sliding window approach.</li>
 	<li>Convert the input and output sequences to numpy arrays and reshape the input data.</li>
 	<li>Define and compile the LSTM model using Keras' Sequential API, with two LSTM layers, two Dense layers, and the Adam optimizer.</li>
 	<li>Train the LSTM model with the training data using a batch size of 1 and 1 epoch.</li>
 	<li>Prepare the test data and reshape it to match the input shape of the LSTM model.</li>
 	<li>Make predictions using the LSTM model and reverse the normalization to obtain the original temperature values.</li>
 	<li>Create a DataFrame for the predicted data and append it to the original data.</li>
 	<li>Write the predicted data to an output CSV file named 'output.csv'.</li>
 	<li>Plot the original data and the predicted data using matplotlib</li>
</ol>
The script demonstrates how to use an LSTM model to predict time series data, in this case, temperature values, and visualize the results. It can be adapted to work with other types of time series data, such as stock prices or weather data, by modifying the input data and preprocessing steps as needed.