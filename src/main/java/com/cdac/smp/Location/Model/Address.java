package com.cdac.smp.Location.Model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
	
	@Id
	@Generated
	@NotNull
	private String loc_id;
	
	private String loc_name;
	
	private String loc_Code;
		
		

	
}
