import paho.mqtt.client as mqtt



# Define the callback function for receiving MQTT messages
def on_message(client, MQTT_TOPIC_PUB, message):
    # Decode the message payload from bytes to string
    payload = message.payload.decode()
    print("Received message: " + payload)

    # Publish a response message back to the specified topic
    response = "Received: " + payload
    client.publish(MQTT_TOPIC_PUB, response)
    print("Published response: " + response)

def establish_connection(MQTT_BROKER_ADDR, MQTT_BROKER_PORT,MQTT_TOPIC_SUB, MQTT_TOPIC_PUB, message = "Hello, world!"):
    
    
    # Set up the MQTT client and connect to the broker
    client = mqtt.Client()
    client.connect(MQTT_BROKER_ADDR, MQTT_BROKER_PORT)

    # Set up the callback function for receiving messages
    #client.on_message = on_message(client, MQTT_TOPIC_PUB, message)

    # Subscribe to the specified topic
    client.subscribe(MQTT_TOPIC_SUB)

    # Publish a message to the specified topic
    
    client.publish(MQTT_TOPIC_SUB, message)
    print("Published message: " + message)

    # Start the MQTT client loop to process incoming messages
    #client.loop_forever()



if __name__ == '__main__':
    host = '192.168.0.124'
    topic = 'CPS2021/tempoutput'
    port = 1883
    establish_connection(host, port, topic, topic)