package com.cdac.smp.AdminMst.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.smp.AdminMst.Model.Address;
import com.cdac.smp.AdminMst.Query.AddressQuery;

@Repository
public class AddressDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Address> getAllAddresses() {
    	 return jdbcTemplate.query(AddressQuery.GET_ALL_ADDRESSES,
                 (rs, rowNum) -> new Address(
                		 rs.getString("loc_id"),
                         rs.getString("loc_name"),
                         rs.getString("loc_code")
                 ));
    }
}
