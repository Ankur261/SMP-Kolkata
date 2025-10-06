package com.cdac.smp.Location.Model;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Location {

    private String loc_cd;
    private String loc_name;
    @Size(min=1,max=3)
  
    private String loc_srt_nm;
    private String sec_cd;
    private String aloc_map_cd;
    private String status;
    private LocalDateTime created_dt;
    private String created_by;
    private LocalDateTime last_updated_dt;
    private String last_updated_by;
    private String meta_status;
    private String meta_remarks;
    
    @Transient 
    private List<Documents> documents;
    
    
  
}
