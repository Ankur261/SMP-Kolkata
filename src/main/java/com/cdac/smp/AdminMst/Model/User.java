package com.cdac.smp.AdminMst.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @JsonProperty("user_id")
    @NotBlank(message = "User ID is required")
    private String userId;

    @JsonProperty("user_name")
    @NotBlank(message = "User Name is required")
    @Size(max = 100, message = "User name cannot exceed 100 characters")
    private String userName;

    @JsonProperty("login_id")
    @NotBlank(message = "Login ID is required")
    private String loginId;

    @Email(message = "Invalid email format")
    private String email;

    @JsonProperty("mobile_number")
    @Pattern(regexp = "^[0-9]{10,12}$", message = "Mobile number must be 10–12 digits")
    private String mobileNumber;

    @Size(min = 6, max = 20, message = "Password must be 6–20 characters")
    private String password;

    @NotBlank(message = "Status is required")
    private String status;

    private String division;
    private String designation;

    @JsonProperty("section_cd")
    private String sectionCode;

    @JsonProperty("department")
    private Integer departmentId;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_dt")
    private String createdDate;

    @JsonProperty("last_updated_by")
    private String lastUpdatedBy;

    @JsonProperty("last_updated_dt")
    private LocalDate lastUpdatedDate;

    @JsonProperty("meta_status")
    private String metaStatus;

    @JsonProperty("meta_remarks")
    private String metaRemarks;

    @JsonProperty("agent_cd")
    private String agentCode;

    @JsonProperty("user_type")
    private String userType;
    
    @JsonProperty("flag")
    private Integer flag;

}
