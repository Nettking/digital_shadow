/**
 * File generated by the ThingML IDE
 * /!\Do not edit this file/!\
 * In case of a bug in the generated code,
 * please submit an issue on our GitHub
 **/

package org.thingml.generated;

import no.sintef.jasm.*;
import no.sintef.jasm.ext.*;

import org.thingml.generated.api.*;
import org.thingml.generated.messages.*;

import java.util.*;

//START: @java_import annotation
import java.time.LocalTime;

//END: @java_import annotation

/**
 * Definition for type : Human
 **/
public class Human extends Component implements IHuman_TTYin_temp, IHuman_TTYin_lum_mot, IHuman_TTYout {

private boolean debug = false;
public boolean isDebug() {return debug;}
public void setDebug(boolean debug) {this.debug = debug;}
public String toString() {
String result = "instance " + getName() + "\n";
result += "\tnight_start = " + TimeStuff_night_start_var;
result += "\tnight_end = " + TimeStuff_night_end_var;
result += "";
return result;
}

public synchronized void SwitchOn_via_TTYin_temp(int OnOffMsg_SwitchOn_did_var){
final Event _msg = SwitchOnType.instantiate(OnOffMsg_SwitchOn_did_var);
_msg.setPort(TTYin_temp_port);
receive(_msg);
}

public synchronized void SwitchOff_via_TTYin_temp(int OnOffMsg_SwitchOff_did_var){
final Event _msg = SwitchOffType.instantiate(OnOffMsg_SwitchOff_did_var);
_msg.setPort(TTYin_temp_port);
receive(_msg);
}

public synchronized void set_temperature_via_TTYin_temp(double TemperatureMsg_set_temperature_t_var){
final Event _msg = set_temperatureType.instantiate(TemperatureMsg_set_temperature_t_var);
_msg.setPort(TTYin_temp_port);
receive(_msg);
}

public synchronized void set_delta_via_TTYin_temp(double TemperatureMsg_set_delta_dlta_var){
final Event _msg = set_deltaType.instantiate(TemperatureMsg_set_delta_dlta_var);
_msg.setPort(TTYin_temp_port);
receive(_msg);
}

public synchronized void fetch_temp_via_TTYin_temp(){
final Event _msg = fetch_tempType.instantiate();
_msg.setPort(TTYin_temp_port);
receive(_msg);
}

public synchronized void add_lightsensor_via_TTYin_lum_mot(int LuminanceMsg_add_lightsensor_id_var){
final Event _msg = add_lightsensorType.instantiate(LuminanceMsg_add_lightsensor_id_var);
_msg.setPort(TTYin_lum_mot_port);
receive(_msg);
}

public synchronized void add_motionsensor_via_TTYin_lum_mot(int MotionMsg_add_motionsensor_id_var){
final Event _msg = add_motionsensorType.instantiate(MotionMsg_add_motionsensor_id_var);
_msg.setPort(TTYin_lum_mot_port);
receive(_msg);
}

public synchronized void temperature_via_TTYout(int TemperatureMsg_temperature_id_var, String TemperatureMsg_temperature_txt_var, double TemperatureMsg_temperature_t_var){
final Event _msg = temperatureType.instantiate(TemperatureMsg_temperature_id_var, TemperatureMsg_temperature_txt_var, TemperatureMsg_temperature_t_var);
_msg.setPort(TTYout_port);
receive(_msg);
}

public synchronized void luminance_via_TTYout(int LuminanceMsg_luminance_id_var, double LuminanceMsg_luminance_lum_var){
final Event _msg = luminanceType.instantiate(LuminanceMsg_luminance_id_var, LuminanceMsg_luminance_lum_var);
_msg.setPort(TTYout_port);
receive(_msg);
}

public synchronized void motion_via_TTYout(int MotionMsg_motion_id_var){
final Event _msg = motionType.instantiate(MotionMsg_motion_id_var);
_msg.setPort(TTYout_port);
receive(_msg);
}

public synchronized void nomotion_via_TTYout(int MotionMsg_nomotion_id_var){
final Event _msg = nomotionType.instantiate(MotionMsg_nomotion_id_var);
_msg.setPort(TTYout_port);
receive(_msg);
}

public synchronized void prompt_via_TTYout(String GeneralMsg_prompt_txt_var){
final Event _msg = promptType.instantiate(GeneralMsg_prompt_txt_var);
_msg.setPort(TTYout_port);
receive(_msg);
}

private void sendSwitchOn_via_send_cmd_temp(int OnOffMsg_SwitchOn_did_var){
send_cmd_temp_port.send(SwitchOnType.instantiate(OnOffMsg_SwitchOn_did_var));
}

private void sendSwitchOff_via_send_cmd_temp(int OnOffMsg_SwitchOff_did_var){
send_cmd_temp_port.send(SwitchOffType.instantiate(OnOffMsg_SwitchOff_did_var));
}

private void sendSet_temperature_via_send_cmd_temp(double TemperatureMsg_set_temperature_t_var){
send_cmd_temp_port.send(set_temperatureType.instantiate(TemperatureMsg_set_temperature_t_var));
}

private void sendSet_delta_via_send_cmd_temp(double TemperatureMsg_set_delta_dlta_var){
send_cmd_temp_port.send(set_deltaType.instantiate(TemperatureMsg_set_delta_dlta_var));
}

private void sendFetch_temp_via_send_cmd_temp(){
send_cmd_temp_port.send(fetch_tempType.instantiate());
}

private void sendAdd_lightsensor_via_send_cmd_lum_mot(int LuminanceMsg_add_lightsensor_id_var){
send_cmd_lum_mot_port.send(add_lightsensorType.instantiate(LuminanceMsg_add_lightsensor_id_var));
}

private void sendAdd_motionsensor_via_send_cmd_lum_mot(int MotionMsg_add_motionsensor_id_var){
send_cmd_lum_mot_port.send(add_motionsensorType.instantiate(MotionMsg_add_motionsensor_id_var));
}

private void sendSet_temperature_via_send_temp(double TemperatureMsg_set_temperature_t_var){
send_temp_port.send(set_temperatureType.instantiate(TemperatureMsg_set_temperature_t_var));
}

private void sendSet_day_start_via_send_day_night_time(int LuminanceMsg_set_day_start_time_var){
send_day_night_time_port.send(set_day_startType.instantiate(LuminanceMsg_set_day_start_time_var));
}

private void sendSet_night_start_via_send_day_night_time(int LuminanceMsg_set_night_start_time_var){
send_day_night_time_port.send(set_night_startType.instantiate(LuminanceMsg_set_night_start_time_var));
}

private void sendTemperature_via_get_values(int TemperatureMsg_temperature_id_var, String TemperatureMsg_temperature_txt_var, double TemperatureMsg_temperature_t_var){
get_values_port.send(temperatureType.instantiate(TemperatureMsg_temperature_id_var, TemperatureMsg_temperature_txt_var, TemperatureMsg_temperature_t_var));
}

private void sendLuminance_via_get_values(int LuminanceMsg_luminance_id_var, double LuminanceMsg_luminance_lum_var){
get_values_port.send(luminanceType.instantiate(LuminanceMsg_luminance_id_var, LuminanceMsg_luminance_lum_var));
}

private void sendMotion_via_get_values(int MotionMsg_motion_id_var){
get_values_port.send(motionType.instantiate(MotionMsg_motion_id_var));
}

private void sendNomotion_via_get_values(int MotionMsg_nomotion_id_var){
get_values_port.send(nomotionType.instantiate(MotionMsg_nomotion_id_var));
}

private void sendPrompt_via_get_values(String GeneralMsg_prompt_txt_var){
get_values_port.send(promptType.instantiate(GeneralMsg_prompt_txt_var));
}

//Attributes
private long TimeStuff_night_end_var;
private long TimeStuff_night_start_var;
//Ports
private Port send_cmd_temp_port;
private Port send_cmd_lum_mot_port;
private Port send_temp_port;
private Port send_day_night_time_port;
private Port TTYin_temp_port;
private Port TTYin_lum_mot_port;
private Port get_values_port;
private Port TTYout_port;
//Message types
protected final TemperatureMessageType temperatureType = new TemperatureMessageType();
protected final Add_thermometerMessageType add_thermometerType = new Add_thermometerMessageType();
protected final Set_temperatureMessageType set_temperatureType = new Set_temperatureMessageType();
protected final Set_deltaMessageType set_deltaType = new Set_deltaMessageType();
protected final Fetch_tempMessageType fetch_tempType = new Fetch_tempMessageType();
protected final PromptMessageType promptType = new PromptMessageType();
protected final SensorinfoMessageType sensorinfoType = new SensorinfoMessageType();
protected final DeviceinfoMessageType deviceinfoType = new DeviceinfoMessageType();
protected final Add_deviceMessageType add_deviceType = new Add_deviceMessageType();
protected final SwitchOnMessageType SwitchOnType = new SwitchOnMessageType();
protected final SwitchOffMessageType SwitchOffType = new SwitchOffMessageType();
protected final LuminanceMessageType luminanceType = new LuminanceMessageType();
protected final Add_lightsensorMessageType add_lightsensorType = new Add_lightsensorMessageType();
protected final Set_luminanceMessageType set_luminanceType = new Set_luminanceMessageType();
protected final Set_day_startMessageType set_day_startType = new Set_day_startMessageType();
protected final Set_night_startMessageType set_night_startType = new Set_night_startMessageType();
protected final MotionMessageType motionType = new MotionMessageType();
protected final NomotionMessageType nomotionType = new NomotionMessageType();
protected final Add_motionsensorMessageType add_motionsensorType = new Add_motionsensorMessageType();
//Empty Constructor
public Human() {
super();
}

//Getters and Setters for non readonly/final attributes
public long getTimeStuff_night_end_var() {
return TimeStuff_night_end_var;
}

public void setTimeStuff_night_end_var(long TimeStuff_night_end_var) {
this.TimeStuff_night_end_var = TimeStuff_night_end_var;
}

public Human initTimeStuff_night_end_var(long TimeStuff_night_end_var) {
this.TimeStuff_night_end_var = TimeStuff_night_end_var;
return this;
}

public long getTimeStuff_night_start_var() {
return TimeStuff_night_start_var;
}

public void setTimeStuff_night_start_var(long TimeStuff_night_start_var) {
this.TimeStuff_night_start_var = TimeStuff_night_start_var;
}

public Human initTimeStuff_night_start_var(long TimeStuff_night_start_var) {
this.TimeStuff_night_start_var = TimeStuff_night_start_var;
return this;
}

//Getters for Ports
public Port getSend_cmd_temp_port() {
return send_cmd_temp_port;
}
public Port getSend_cmd_lum_mot_port() {
return send_cmd_lum_mot_port;
}
public Port getSend_temp_port() {
return send_temp_port;
}
public Port getSend_day_night_time_port() {
return send_day_night_time_port;
}
public Port getTTYin_temp_port() {
return TTYin_temp_port;
}
public Port getTTYin_lum_mot_port() {
return TTYin_lum_mot_port;
}
public Port getGet_values_port() {
return get_values_port;
}
public Port getTTYout_port() {
return TTYout_port;
}
private CompositeState buildHuman_HumanBehavior(){
final AtomicState state_Human_HumanBehavior_Idle = new AtomicState("Idle");
Transition h699967913 = new Transition();
h699967913.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h699967913.event(add_motionsensorType);
h699967913.port(TTYin_lum_mot_port);
h699967913.action((Event e)->{
final Add_motionsensorMessageType.Add_motionsensorMessage add_motionsensor = (Add_motionsensorMessageType.Add_motionsensorMessage) e;
sendAdd_motionsensor_via_send_cmd_lum_mot((int) (add_motionsensor.id));
});

Transition h942704433 = new Transition();
h942704433.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h942704433.event(add_lightsensorType);
h942704433.port(TTYin_lum_mot_port);
h942704433.action((Event e)->{
final Add_lightsensorMessageType.Add_lightsensorMessage add_lightsensor = (Add_lightsensorMessageType.Add_lightsensorMessage) e;
sendAdd_lightsensor_via_send_cmd_lum_mot((int) (add_lightsensor.id));
});

Transition h1963592702 = new Transition();
h1963592702.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h1963592702.event(SwitchOnType);
h1963592702.port(TTYin_temp_port);
h1963592702.action((Event e)->{
final SwitchOnMessageType.SwitchOnMessage SwitchOn = (SwitchOnMessageType.SwitchOnMessage) e;
sendSwitchOn_via_send_cmd_temp((int) (SwitchOn.did));
});

Transition h1485292457 = new Transition();
h1485292457.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h1485292457.event(SwitchOffType);
h1485292457.port(TTYin_temp_port);
h1485292457.action((Event e)->{
final SwitchOffMessageType.SwitchOffMessage SwitchOff = (SwitchOffMessageType.SwitchOffMessage) e;
sendSwitchOff_via_send_cmd_temp((int) (SwitchOff.did));
});

Transition h356564577 = new Transition();
h356564577.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h356564577.event(set_temperatureType);
h356564577.port(TTYin_temp_port);
h356564577.action((Event e)->{
final Set_temperatureMessageType.Set_temperatureMessage set_temperature = (Set_temperatureMessageType.Set_temperatureMessage) e;
sendSet_temperature_via_send_cmd_temp((double) (set_temperature.t));
});

Transition h1514165394 = new Transition();
h1514165394.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h1514165394.event(set_deltaType);
h1514165394.port(TTYin_temp_port);
h1514165394.action((Event e)->{
final Set_deltaMessageType.Set_deltaMessage set_delta = (Set_deltaMessageType.Set_deltaMessage) e;
sendSet_delta_via_send_cmd_temp((double) (set_delta.dlta));
});

Transition h445801139 = new Transition();
h445801139.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h445801139.event(fetch_tempType);
h445801139.port(TTYin_temp_port);
h445801139.action((Event e)->{
sendFetch_temp_via_send_cmd_temp();
});

Transition h1076564143 = new Transition();
h1076564143.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h1076564143.event(temperatureType);
h1076564143.port(TTYout_port);
h1076564143.action((Event e)->{
final TemperatureMessageType.TemperatureMessage temperature = (TemperatureMessageType.TemperatureMessage) e;
sendTemperature_via_get_values((int) (temperature.id), (String) (temperature.txt), (double) (temperature.t));
});

Transition h255720615 = new Transition();
h255720615.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h255720615.event(luminanceType);
h255720615.port(TTYout_port);
h255720615.action((Event e)->{
final LuminanceMessageType.LuminanceMessage luminance = (LuminanceMessageType.LuminanceMessage) e;
sendLuminance_via_get_values((int) (luminance.id), (double) (luminance.lum));
});

Transition h804744684 = new Transition();
h804744684.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h804744684.event(motionType);
h804744684.port(TTYout_port);
h804744684.action((Event e)->{
final MotionMessageType.MotionMessage motion = (MotionMessageType.MotionMessage) e;
sendMotion_via_get_values((int) (motion.id));
});

Transition h797083826 = new Transition();
h797083826.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h797083826.event(nomotionType);
h797083826.port(TTYout_port);
h797083826.action((Event e)->{
final NomotionMessageType.NomotionMessage nomotion = (NomotionMessageType.NomotionMessage) e;
sendNomotion_via_get_values((int) (nomotion.id));
});

Transition h1874143156 = new Transition();
h1874143156.from(state_Human_HumanBehavior_Idle).to(state_Human_HumanBehavior_Idle);
h1874143156.event(promptType);
h1874143156.port(TTYout_port);
h1874143156.action((Event e)->{
final PromptMessageType.PromptMessage prompt = (PromptMessageType.PromptMessage) e;
System.out.println(""+(prompt.txt));
});

final CompositeState state_Human_HumanBehavior = new CompositeState("HumanBehavior");
state_Human_HumanBehavior.onEntry(()->{
});
state_Human_HumanBehavior.add(state_Human_HumanBehavior_Idle);
state_Human_HumanBehavior.initial(state_Human_HumanBehavior_Idle);
return state_Human_HumanBehavior;
}

public Component buildBehavior(String session, Component root) {
if (root == null) {
//Init ports
send_cmd_temp_port = new Port("send_cmd_temp", this);
send_cmd_lum_mot_port = new Port("send_cmd_lum_mot", this);
send_temp_port = new Port("send_temp", this);
send_day_night_time_port = new Port("send_day_night_time", this);
TTYin_temp_port = new Port("TTYin_temp", this);
TTYin_lum_mot_port = new Port("TTYin_lum_mot", this);
get_values_port = new Port("get_values", this);
TTYout_port = new Port("TTYout", this);
} else {
send_cmd_temp_port = ((Human)root).send_cmd_temp_port;
send_cmd_lum_mot_port = ((Human)root).send_cmd_lum_mot_port;
send_temp_port = ((Human)root).send_temp_port;
send_day_night_time_port = ((Human)root).send_day_night_time_port;
TTYin_temp_port = ((Human)root).TTYin_temp_port;
TTYin_lum_mot_port = ((Human)root).TTYin_lum_mot_port;
get_values_port = ((Human)root).get_values_port;
TTYout_port = ((Human)root).TTYout_port;
}
if (session == null){
//Init state machine
behavior = buildHuman_HumanBehavior();
}
return this;
}

 long Now() {
long timeval_var = 0;
timeval_var = (long) (LocalTime.now().toNanoOfDay()/1000000);
return (long) (timeval_var);
}
 long Timestamp(final int TimeStuff_Timestamp_h_var, final int TimeStuff_Timestamp_m_var, final int TimeStuff_Timestamp_s_var) {
return (long) ((TimeStuff_Timestamp_s_var + TimeStuff_Timestamp_m_var * 60 + TimeStuff_Timestamp_h_var * 60 * 60) * 1000);
}
 long LeftOfDay() {
long n_var = (long) (Now());

long left_var = (long) (getTimeStuff_night_start_var() - n_var);

if(left_var < 0) {
left_var = (long) (left_var + 24 * 60 * 60 * 1000);

}
if( !(Night((long) (n_var)))) {
return (long) (left_var);

} else {
return (long) (0);

}
}
 long LeftOfNight() {
long n_var = (long) (Now());

long left_var = (long) (getTimeStuff_night_end_var() - n_var);

if(left_var < 0) {
left_var = (long) (left_var + 24 * 60 * 60 * 1000);

}
if(Night((long) (n_var))) {
return (long) (left_var);

} else {
return (long) (0);

}
}
 boolean Night(final long TimeStuff_Night_x_var) {
if(getTimeStuff_night_end_var() < getTimeStuff_night_start_var()) {
if(getTimeStuff_night_start_var() <= TimeStuff_Night_x_var || TimeStuff_Night_x_var < getTimeStuff_night_end_var()) {
return (boolean) (true);

} else {
return (boolean) (false);

}

} else {
if(getTimeStuff_night_start_var() <= TimeStuff_Night_x_var && TimeStuff_Night_x_var < getTimeStuff_night_end_var()) {
return (boolean) (true);

} else {
return (boolean) (false);

}

}
}
 boolean UpdateNightStart(final long TimeStuff_UpdateNightStart_x_var, final long TimeStuff_UpdateNightStart_new_night_start_var) {
System.out.println(""+((" Old night start: ")));
System.out.println(""+((getTimeStuff_night_start_var())));
TimeStuff_night_start_var = (long) (TimeStuff_UpdateNightStart_new_night_start_var);
System.out.println(""+((" New night start: ")));
System.out.println(""+((getTimeStuff_night_start_var())));
boolean isNight_var = (boolean) (Night((long) (TimeStuff_UpdateNightStart_x_var)));

System.out.println(""+((isNight_var)));
return (boolean) (isNight_var);
}
 boolean UpdateNightEnd(final long TimeStuff_UpdateNightEnd_x_var, final long TimeStuff_UpdateNightEnd_new_night_end_var) {
System.out.println(""+((" Old night_end: ")));
System.out.println(""+((getTimeStuff_night_start_var())));
TimeStuff_night_end_var = (long) (TimeStuff_UpdateNightEnd_new_night_end_var);
System.out.println(""+((" New night_end: ")));
System.out.println(""+((getTimeStuff_night_start_var())));
boolean isNight_var = (boolean) (Night((long) (TimeStuff_UpdateNightEnd_x_var)));

System.out.println(""+((isNight_var)));
return (boolean) (isNight_var);
}
}