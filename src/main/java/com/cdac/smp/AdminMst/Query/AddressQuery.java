package com.cdac.smp.AdminMst.Query;

import org.springframework.stereotype.Component;

@Component
public class AddressQuery {
    public static final String GET_ALL_ADDRESSES =
    		"""
    			SELECT loc_id, loc_name, loc_code FROM "Address" ORDER BY loc_name
    		""";
}

