package com.cdac.smp.Location.Queries;

public interface LocationQueries {

    public static final String INSERT_LOCATION = """
        INSERT INTO ems_prop_loc_mst (
            loc_cd,
            loc_name,
            loc_srt_nm,
            sec_cd,
            aloc_map_cd,
            status,
            created_dt,
            created_by,
            last_updated_dt,
            last_updated_by,
            meta_status,
            meta_remarks
        ) VALUES (
            :loc_cd,
            :loc_name,
            :loc_srt_nm,
            :sec_cd,
            :aloc_map_cd,
            :status,
            :created_dt,
            :created_by,
            :last_updated_dt,
            :last_updated_by,
            :meta_status,
            :meta_remarks
        )
    """;
    
    
    
    public static final String UPDATE_LOCATION = """
    	    UPDATE ems_prop_loc_mst
    	    SET loc_name = :loc_name,
    	        loc_srt_nm = :loc_srt_nm,
    	        sec_cd = :sec_cd,
    	        aloc_map_cd = :aloc_map_cd,
    	        status = :status,
    	        last_updated_dt = :last_updated_dt,
    	        last_updated_by = :last_updated_by,
    	        meta_status = :meta_status,
    	        meta_remarks = :meta_remarks
    	    WHERE loc_cd = :loc_cd
    	""";
    public static final String SOFT_DELETE_LOCATION = """
    	    UPDATE ems_prop_loc_mst
    	    SET status = 'INACTIVE',
    	        last_updated_dt = NOW(),
    	        last_updated_by = 'SYSTEM',
    	        meta_status = 'D',
    	        meta_remarks = 'Soft deleted'
    	    WHERE loc_cd = :loc_cd
    	""";

    public static final String VIEW_LOCATION = """
    	    SELECT * FROM ems_prop_loc_mst
    	    WHERE status != 'INACTIVE'
    	    ORDER BY loc_cd
    	""";
    

    public static final String Get_ADDRESS = """
    	    SELECT loc_name FROM address
    	    WHERE loc_code = :loc_code
    	   
    	""";
    
    public static final String VIEW_ALL_ADDRESS = """
    	    SELECT loc_name,loc_code FROM Address
    	   
    	""";
}
