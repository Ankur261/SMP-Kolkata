package com.cdac.smp.AdminMst.Query;

import org.springframework.stereotype.Component;

@Component
public class UserQueries {

	public static final String INSERT_USER = 
		    "INSERT INTO ems_admin_users " +
		    "(user_id, user_name, login_id, email, mobile_number, password, status, division, designation, section_cd, department, created_by, created_dt, last_updated_by, last_updated_dt, meta_status, meta_remarks, agent_cd, user_type) " +
		    "VALUES " +
		    "(:userId, :userName, :loginId, :email, :mobileNumber, :password, :status, :division, :designation, :sectionCd, :department, :createdBy, :createdDt, :lastUpdatedBy, :lastUpdatedDt, :metaStatus, :metaRemarks, :agentCd, :userType)";

		    public static final String GET_USER_BY_ID = 
		        "SELECT * FROM ems_admin_users WHERE user_id = :userId";

		    public static final String GET_ALL_USERS = 
		        "SELECT * FROM ems_admin_users WHERE meta_status='Y'";

		    public static final String UPDATE_USER = 
		        "UPDATE ems_admin_users SET user_name = :userName, login_id = :loginId, email = :email, "
		        + "mobile_number = :mobileNumber, status = :status,"
		        + " division = :division, designation = :designation, user_type = :userType WHERE user_id = :userId";

		    public static final String DELETE_USER = 
		        "UPDATE ems_admin_users SET meta_status= :metaStatus WHERE user_id = :userId";
		    
		    public static final String LOGIN_USER =
		            "SELECT * FROM ems_admin_users WHERE login_id = :loginId AND password = :password AND meta_status = 'Y'";
		    
		    public static final String GET_USER_BY_LOGINID =
		            "SELECT user_id, user_name, login_id, email, mobile_number, password, status, division " +
		            "FROM ems_admin_users " +
		            "WHERE login_id = :loginId";
		    
		    public static final String INC_FLAG_BY_ONE=
		    		"UPDATE ems_admin_users SET flag = flag +1 WHERE login_id = :loginId";

		    public static final String SET_FLAG_TO_ZERO=
		    		"UPDATE ems_admin_users SET flag = 0 WHERE login_id = :loginId";
		    
		    public static final String GET_FLAG_VALUE=
		    		"SELECT flag from ems_admin_users where login_id=:loginId";
}
