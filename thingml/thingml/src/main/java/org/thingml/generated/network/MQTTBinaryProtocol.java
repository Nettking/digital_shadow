package org.thingml.generated.network;

import org.thingml.generated.messages.*;
import no.sintef.jasm.ext.Event;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.Json;
public class MQTTBinaryProtocol implements StringJava {
private static final LuminanceMessageType LUMINANCE = new LuminanceMessageType();
private static final TemperatureMessageType TEMPERATURE = new TemperatureMessageType();
public Event instantiate(String payload) {
try{
final JsonObject msg = Json.parse(payload).asObject();
final String msgName = msg.names().get(0);
if(msgName.equals(LUMINANCE.getName())){
return LUMINANCE.instantiate((int) msg.get(msgName).asObject().get("id").asInt(), (double) msg.get(msgName).asObject().get("lum").asDouble());
}
else if(msgName.equals(TEMPERATURE.getName())){
return TEMPERATURE.instantiate((int) msg.get(msgName).asObject().get("id").asInt(), (String) msg.get(msgName).asObject().get("txt").asString(), (double) msg.get(msgName).asObject().get("t").asDouble());
}

}catch(Exception pe){
System.out.println("Cannot parse " + payload + " because of " + pe.getMessage());
}
return null;
}
public String format(Event e){
if (e.getType().equals(SWITCHON)) {
return format((SwitchOnMessageType.SwitchOnMessage)e)
;}
else if (e.getType().equals(SWITCHOFF)) {
return format((SwitchOffMessageType.SwitchOffMessage)e)
;}
return null;
}
private static final SwitchOnMessageType SWITCHON = new SwitchOnMessageType();
/**Serializes a message into a JSON format*/
private String format(final SwitchOnMessageType.SwitchOnMessage _this) {
final JsonObject msg = new JsonObject();
final JsonObject params = new JsonObject();
params.add("did", _this.did);
msg.add("SwitchOn",params);
return msg.toString();
}

private static final SwitchOffMessageType SWITCHOFF = new SwitchOffMessageType();
/**Serializes a message into a JSON format*/
private String format(final SwitchOffMessageType.SwitchOffMessage _this) {
final JsonObject msg = new JsonObject();
final JsonObject params = new JsonObject();
params.add("did", _this.did);
msg.add("SwitchOff",params);
return msg.toString();
}



}
