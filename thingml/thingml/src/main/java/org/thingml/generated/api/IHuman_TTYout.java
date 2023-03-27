package org.thingml.generated.api;

import org.thingml.generated.api.*;

public interface IHuman_TTYout{
void temperature_via_TTYout(int TemperatureMsg_temperature_id_var, String TemperatureMsg_temperature_txt_var, double TemperatureMsg_temperature_t_var);
void luminance_via_TTYout(int LuminanceMsg_luminance_id_var, double LuminanceMsg_luminance_lum_var);
void motion_via_TTYout(int MotionMsg_motion_id_var);
void nomotion_via_TTYout(int MotionMsg_nomotion_id_var);
void prompt_via_TTYout(String GeneralMsg_prompt_txt_var);
}