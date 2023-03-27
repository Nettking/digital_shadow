package org.thingml.generated.api;

import org.thingml.generated.api.*;

public interface IHuman_get_valuesClient{
void temperature_from_get_values(int TemperatureMsg_temperature_id_var, String TemperatureMsg_temperature_txt_var, double TemperatureMsg_temperature_t_var);
void luminance_from_get_values(int LuminanceMsg_luminance_id_var, double LuminanceMsg_luminance_lum_var);
void motion_from_get_values(int MotionMsg_motion_id_var);
void nomotion_from_get_values(int MotionMsg_nomotion_id_var);
void prompt_from_get_values(String GeneralMsg_prompt_txt_var);
}