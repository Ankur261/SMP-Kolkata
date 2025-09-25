package com.cdac.smp.AdminMst.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	private int id;
	private String userName;
	private String loginId;
	private String email;
	private String mobileNumber;
	private String password;
	private String status;
	private String division;
	private String designation;
	private String sectionCode;
	private int departmentId;
	private String createdBy;
	private String lastUpdatedBy;
	private String lastUpdatedDate;
	private String metaStatus;
	private String metaRemarks;
	private String agentCode;
	private String userType;// for internal / external i/E
	
}
