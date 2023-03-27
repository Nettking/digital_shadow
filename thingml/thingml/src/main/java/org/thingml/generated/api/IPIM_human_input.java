package org.thingml.generated.api;

import org.thingml.generated.api.*;

public interface IPIM_human_input{
void SwitchOn_via_human_input(int OnOffMsg_SwitchOn_did_var);
void SwitchOff_via_human_input(int OnOffMsg_SwitchOff_did_var);
void set_temperature_via_human_input(double TemperatureMsg_set_temperature_t_var);
void set_delta_via_human_input(double TemperatureMsg_set_delta_dlta_var);
void fetch_temp_via_human_input();
}