package com.cdac.smp.AdminMst.Dao;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.smp.AdminMst.Query.LoginEntryQueries;

@Repository
public class LoginEntryDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LoginEntryDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveFailedLogin(String loginId) {
        Map<String, Object> params = new HashMap<>();
        params.put("loginId", loginId);
        params.put("loginTime", Timestamp.valueOf(LocalDateTime.now()));

        jdbcTemplate.update(LoginEntryQueries.INSERT_FAILED_LOGIN, params);
    }
//	public  String getTime(String loginId) {
//		 Map<String, Object> params = new HashMap<>();
//	     params.put("loginId", loginId);
//		return JdbcTemplate.queryForObject(LoginEntryQueries.GET_DATE,params,LocalDate.class);
//	}
	
	public LocalDateTime getTime(String loginId) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("loginId", loginId);

	    try {
	        return jdbcTemplate.queryForObject(
	            LoginEntryQueries.GET_DATE,
	            params,
	            LocalDateTime.class
	        );
	    } catch (org.springframework.dao.EmptyResultDataAccessException e) {
	        return null;
	    }
	}
	
	public void deleteLoginEntries(String loginId) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("loginId", loginId);
	    jdbcTemplate.update(LoginEntryQueries.DELETE_LOGIN_ENTRIES, params);
	}

}
