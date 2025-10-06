package com.cdac.smp.AdminMst.Query;

import java.time.LocalDate;

public class LoginEntryQueries {

    public static final String INSERT_FAILED_LOGIN =
        "INSERT INTO login_entry (login_id, login_time) VALUES (:loginId, :loginTime)";

    public static final String GET_LOGINS_BY_USER =
        "SELECT * FROM login_entry WHERE login_id = :loginId ORDER BY login_time DESC";
    
    public static final String  GET_DATE=
    	    "SELECT login_time FROM login_entry WHERE login_id = :loginId ORDER BY login_time DESC LIMIT 1";
    
    public static final String DELETE_LOGIN_ENTRIES =
            "DELETE FROM login_entry WHERE login_id = :loginId";

}