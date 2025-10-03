package com.cdac.smp.Location.Dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.smp.Location.Model.Address;
import com.cdac.smp.Location.Model.Location;
import com.cdac.smp.Location.Queries.LocationQueries;

@Repository
public class AddressDao implements LocationQueries{
	
	@Autowired
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private RowMapper<Address> addressRowMapper(){
		
		return(rs,rownum)->{
			Address address = new Address();
			address.setLoc_Code(rs.getString("loc_code"));
			address.setLoc_name(rs.getString("loc_name"));
			
			return address;
			
			};
		}
	
	public List<Address> getAllAddresses(){
		try {
			return namedParameterJdbcTemplate.query(VIEW_ALL_ADDRESS, addressRowMapper());
		}catch(DataAccessException e) {
			e.printStackTrace();
			return List.of();
		}
				}
 public Address getAddressByCode(String loc_code) {
	 try {
		return namedParameterJdbcTemplate.queryForObject(
				Get_ADDRESS,
				new MapSqlParameterSource("loc_code",loc_code),
				addressRowMapper()
				) ;
	 }catch(Exception e)
	 {
		 e.printStackTrace();
		 return null;
	 }
	 
 }
  

	






}
