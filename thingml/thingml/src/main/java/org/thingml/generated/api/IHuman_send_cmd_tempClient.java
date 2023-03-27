package org.thingml.generated.api;

import org.thingml.generated.api.*;

public interface IHuman_send_cmd_tempClient{
void SwitchOn_from_send_cmd_temp(int OnOffMsg_SwitchOn_did_var);
void SwitchOff_from_send_cmd_temp(int OnOffMsg_SwitchOff_did_var);
void set_temperature_from_send_cmd_temp(double TemperatureMsg_set_temperature_t_var);
void set_delta_from_send_cmd_temp(double TemperatureMsg_set_delta_dlta_var);
void fetch_temp_from_send_cmd_temp();
}