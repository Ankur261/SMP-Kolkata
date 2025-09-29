package com.cdac.smp.section.queries;

public class SectionQueries {
	
	public final String getAllSection = "SELECT * FROM ems_prop_sec_mst WHERE del_flag='N'" ;
	
	public final String createSection = """
            								INSERT INTO ems_prop_sec_mst 
            								(sec_cd, sec_name, sec_srt_nm, sec_aloc_map_cd, del_flag,
             								created_dt, created_by, last_updated_dt, lat_updated_by,
			 								officer_incharge_cd, officer_incharge_name)
            								VALUES (:secCd, :secName, :secSrtNm, :secAlocMapCd, :delFlag,
                    						:createdDt, :createdBy, :lastUpdatedDt, :lastUpdatedBy,
                    						:officerInchargeCd, :officerInchargeName)
            							""";
	
}
