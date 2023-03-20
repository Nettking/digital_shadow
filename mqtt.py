import paho.mqtt.client as mqtt

switchState = False

def on_message(client, userdata, message):
    # Decode the message payload from bytes to string
    payload = message.payload.decode()
    global switchState
    
    if payload == '{"SwitchOff":{"did":1}}':
        #print('Slår av...')
        switchState = False
    elif payload == '{"SwitchOn":{"did":1}}':
        #print('Slår på...')
        switchState = True

    

# Function to establish a connection to the MQTT broker
def establish_connection(MQTT_BROKER_ADDR, MQTT_BROKER_PORT, MQTT_TOPIC_SUB, MQTT_TOPIC_PUB, message="Hello, world!"):

    # Set up the MQTT client and connect to the broker
    client = mqtt.Client()
    client.connect(MQTT_BROKER_ADDR, MQTT_BROKER_PORT)

    # Subscribe to the specified topic with QoS level 1
    client.subscribe(MQTT_TOPIC_SUB, qos=1)

    # Clear any retained messages on the subscribed topic
    client.publish(MQTT_TOPIC_SUB, payload=None, retain=True)

    # Publish a message to the specified topic with the retain flag set to False and QoS level 1
    client.publish(MQTT_TOPIC_PUB, message, retain=False, qos=1)
    print("Published message: " + message)

    # Set up the callback function for receiving messages
    client.on_message = on_message

    return client



if __name__ == '__main__':
    host = '192.168.0.124'
    topic = 'CPS2021/tempoutput'
    port = 1883
    establish_connection(host, port, topic, topic)