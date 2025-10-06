package com.cdac.smp.section.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.smp.section.model.Address;
import com.cdac.smp.section.queries.AddressQueries;

@Repository
public class AddressDao {
	
	Address address ;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	AddressDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate ) {
		this.namedParameterJdbcTemplate =namedParameterJdbcTemplate;
	}
	
	AddressQueries addressQueries = new AddressQueries();
	
	public List<Address> getAllAddress() {
	    
	    return namedParameterJdbcTemplate.query(addressQueries.getAllAddress, Collections.emptyMap(),
	            (rs, rowNum) -> {
	                Address address = new Address();
	                address.setLocationId(rs.getString("loc_id"));
	                address.setLocationName(rs.getString("loc_name"));
	                address.setLocationCode(rs.getString("loc_code"));
	                return address;
	            });
	}
	
	

}
