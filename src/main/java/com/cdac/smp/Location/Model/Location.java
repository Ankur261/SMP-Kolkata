package com.cdac.smp.Location.Model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {

	private String loc_cd;
	private String loc_name;
	private String loc_srt_nm;
	private String sec_cd;
	private String aloc_map_cd;
	private String status;
	private LocalDateTime created_dt;
	private String created_by;
	private LocalDateTime last_updated_at;
	private String last_updated_by;
	private String meta_status;
	private String meta_marks;
	
	

}
