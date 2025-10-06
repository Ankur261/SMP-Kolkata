package com.cdac.smp.AdminMst.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@JsonProperty("loc_id")
    private String locId;
	@JsonProperty("loc_name")
    private String locName;
	@JsonProperty("loc_code")
    private String locCode;
}
