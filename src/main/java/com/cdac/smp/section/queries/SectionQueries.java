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
	public final String getSectionByCode = "SELECT sec_cd, sec_name, sec_srt_nm, sec_aloc_map_cd, del_flag, " +
            				  			   "created_dt, created_by, last_updated_dt, lat_updated_by, " +
            				  			   "officer_incharge_cd, officer_incharge_name " +
            				  			   "FROM ems_prop_sec_mst WHERE sec_cd = :sec_cd";
	
	public final String updateSection = "UPDATE ems_prop_sec_mst " +
            							"SET sec_name = :secName, " +
            							"    sec_srt_nm = :secShortName, " +
            							"    sec_aloc_map_cd = :secAlocMapCd, " +
            							"    last_updated_dt = NOW(), " +
            							"    lat_updated_by = :lastUpdatedBy, " +
            							"    officer_incharge_cd = :officerInchargeCd, " +
            							"    officer_incharge_name = :officerInchargeName " +
            							"WHERE sec_cd = :secCd";
	
	public final String deleteSection = """
			    			  			UPDATE ems_prop_sec_mst 
			    			  			SET del_flag = 'Y', last_updated_dt = NOW() 
			    			  			WHERE sec_cd = :secCd
							  			""";
	
}
