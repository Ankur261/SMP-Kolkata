package com.cdac.smp.AdminMst.Dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.smp.AdminMst.Model.User;
import com.cdac.smp.AdminMst.Query.UserQueries;

@Repository
public class UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private Map<String, Object> mapUserParams(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", user.getUserId());
        params.put("userName", user.getUserName());
        params.put("loginId", user.getLoginId());
        params.put("email", user.getEmail());
        params.put("mobileNumber", user.getMobileNumber());
        params.put("password", user.getPassword());
        params.put("status", user.getStatus());
        params.put("division", user.getDivision());
        params.put("designation", user.getDesignation());
        params.put("sectionCd", user.getSectionCode());
        params.put("department", user.getDepartmentId());
        params.put("createdBy", "Admin");
        params.put("createdDt",LocalDate.now());
        params.put("lastUpdatedBy", user.getUserName());
        params.put("lastUpdatedDt", LocalDate.now());
        params.put("metaStatus", "Y");
        params.put("metaRemarks", user.getMetaRemarks());
        params.put("agentCd", user.getAgentCode());
        params.put("userType", user.getUserType());
        return params;
    }


    public int save(User user) {
        
        return jdbcTemplate.update(UserQueries.INSERT_USER, mapUserParams(user));
    }

    public Optional<User> findById(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        List<User> list = jdbcTemplate.query(UserQueries.GET_USER_BY_ID, params,
                new BeanPropertyRowMapper<>(User.class));
        return list.stream().findFirst();
    }

    public List<User> findAll() {
        return jdbcTemplate.query(UserQueries.GET_ALL_USERS, new BeanPropertyRowMapper<>(User.class));
    }

    public int update(User user) {
       
        return jdbcTemplate.update(UserQueries.UPDATE_USER, mapUserParams(user));
    }

    public int softDeleteUser(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("metaStatus","N");
        return jdbcTemplate.update(UserQueries.DELETE_USER, params);
    }

    public Optional<User> login(String loginId, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("loginId", loginId);
        params.put("password", password);

        List<User> list = jdbcTemplate.query(UserQueries.LOGIN_USER, params,
                new BeanPropertyRowMapper<>(User.class));
        return list.stream().findFirst();
    }

    public User getUserByLoginId(String loginId) {
        Map<String, Object> params = new HashMap<>();
        params.put("loginId", loginId);
        List<User> list = jdbcTemplate.query(UserQueries.GET_USER_BY_LOGINID, params,
                new BeanPropertyRowMapper<>(User.class));
        return list.stream().findFirst().orElse(null);
    }

	public void setFlagByOne(String loginId) {
		Map<String, Object> params = new HashMap<>();
	    params.put("loginId", loginId);
		jdbcTemplate.update(UserQueries.INC_FLAG_BY_ONE,params);
		
	}

	public int getFlagValue(String loginId) {
		Map<String, Object> params = new HashMap<>();
	    params.put("loginId", loginId);
	    Integer value=jdbcTemplate.queryForObject(UserQueries.GET_FLAG_VALUE,params,Integer.class);
	    
	    return value;
	}

	public void setFlagToZero(String loginId) {
		Map<String, Object> params = new HashMap<>();
	    params.put("loginId", loginId);
		jdbcTemplate.update(UserQueries.SET_FLAG_TO_ZERO,params);
	}

}
