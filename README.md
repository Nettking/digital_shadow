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