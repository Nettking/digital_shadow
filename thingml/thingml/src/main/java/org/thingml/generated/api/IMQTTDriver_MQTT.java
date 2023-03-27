package org.thingml.generated.api;

import org.thingml.generated.api.*;

public interface IMQTTDriver_MQTT{
void temperature_via_MQTT(int TemperatureMsg_temperature_id_var, String TemperatureMsg_temperature_txt_var, double TemperatureMsg_temperature_t_var);
void luminance_via_MQTT(int LuminanceMsg_luminance_id_var, double LuminanceMsg_luminance_lum_var);
}