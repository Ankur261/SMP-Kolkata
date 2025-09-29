package com.cdac.smp.section.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Section {

    @NotBlank(message = "Section code is required")
    @Size(max = 10, message = "Section code must not exceed 10 characters")
    private String sectionCode;

    @NotBlank(message = "Section name is required")
    @Size(max = 100, message = "Section name must not exceed 100 characters")
    private String sectionName;

    @Size(max = 3, message = "Short name must be up to 3 characters")
    private String sectionShortName;

    @Size(max = 3, message = "Allocation map code must be up to 3 characters")
    private String secAloMapCode;

    private String deleteFlag;

    private LocalDateTime createdDate;

    @NotBlank(message = "Created by is required")
    @Size(max = 50, message = "Created by must not exceed 50 characters")
    private String createdBy;

    private LocalDateTime lastUpdatedDateTime;

    private String lastUpdatedBy;

    @Size(max = 10, message = "Officer Incharge Code must not exceed 10 characters")
    private String officerInchargeCode;

    @Size(max = 100, message = "Officer Incharge Name must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Officer Incharge Name must only contain letters and spaces")
    private String officerInchargeName;
}
