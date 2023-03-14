import paho.mqtt.client as mqtt

# Function to establish a connection to the MQTT broker
def establish_connection(MQTT_BROKER_ADDR, MQTT_BROKER_PORT,MQTT_TOPIC_SUB, MQTT_TOPIC_PUB, message = "Hello, world!"):
    
    # Set up the MQTT client and connect to the broker
    client = mqtt.Client()
    client.connect(MQTT_BROKER_ADDR, MQTT_BROKER_PORT)

    # Subscribe to the specified topic
    client.subscribe(MQTT_TOPIC_SUB)

    # Publish a message to the specified topic
    client.publish(MQTT_TOPIC_PUB, message)
    print("Published message: " + message)

if __name__ == '__main__':
    host = '192.168.0.124'
    topic = 'CPS2021/tempoutput'
    port = 1883
    establish_connection(host, port, topic, topic)