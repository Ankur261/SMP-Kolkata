package com.cdac.smp.section.dao;
import java.time.LocalDate;
import java.util.*;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.smp.section.model.Section;
import com.cdac.smp.section.queries.SectionQueries;

@Repository
public class SectionDao {
	
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate  ;
	
	public SectionDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate =namedParameterJdbcTemplate;
	}
	
	SectionQueries sectionQueries = new SectionQueries();
	
	public List<Section> getAllSection() {
	    
	    return namedParameterJdbcTemplate.query(sectionQueries.getAllSection, Collections.emptyMap(),
	            (rs, rowNum) -> {
	                Section section = new Section();
	                section.setSectionCode(rs.getString("sec_cd"));
	                section.setSectionName(rs.getString("sec_name"));
	                section.setSectionShortName(rs.getString("sec_srt_nm"));
	                section.setSecAloMapCode(rs.getString("sec_aloc_map_cd"));
	                section.setDeleteFlag(rs.getString("del_flag"));
	                section.setCreatedDate(rs.getTimestamp("created_dt").toLocalDateTime());
	                section.setCreatedBy(rs.getString("created_by"));
	                section.setLastUpdatedDateTime(
	                        rs.getTimestamp("last_updated_dt") != null ? rs.getTimestamp("last_updated_dt").toLocalDateTime() : null
	                );
	                section.setLastUpdatedBy(rs.getString("lat_updated_by"));
	                section.setOfficerInchargeCode(rs.getString("officer_incharge_cd"));
	                section.setOfficerInchargeName(rs.getString("officer_incharge_name"));
	                return section;
	            });
	}

	
	public int createSection(Section section) {
        

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("secCd", section.getSectionCode())
                .addValue("secName", section.getSectionName())
                .addValue("secSrtNm", section.getSectionShortName())
                .addValue("secAlocMapCd", section.getSecAloMapCode())
                .addValue("delFlag", "N")
                .addValue("createdDt", LocalDate.now())
                .addValue("createdBy", section.getCreatedBy())
                .addValue("lastUpdatedDt", LocalDate.now())
                .addValue("lastUpdatedBy", section.getLastUpdatedBy())
                .addValue("officerInchargeCd", section.getOfficerInchargeCode())
                .addValue("officerInchargeName", section.getOfficerInchargeName());

        return namedParameterJdbcTemplate.update(sectionQueries.createSection, params);
    }
	
	public int deleteSection(String sectionCode) {
		 

			    Map<String, Object> params = new HashMap<>();
			    params.put("secCd", sectionCode);

			    return namedParameterJdbcTemplate.update(sectionQueries.deleteSection, params);
	}
	
	 public int updateSection(Section section) {

	        Map<String, Object> params = new HashMap<>();
	        params.put("secCd", section.getSectionCode());
	        params.put("secName", section.getSectionName());
	        params.put("secShortName", section.getSectionShortName());
	        params.put("secAlocMapCd", section.getSecAloMapCode());
	        params.put("lastUpdatedBy", section.getLastUpdatedBy());
	        params.put("officerInchargeCd", section.getOfficerInchargeCode());
	        params.put("officerInchargeName", section.getOfficerInchargeName());

	        return namedParameterJdbcTemplate.update(sectionQueries.updateSection, params);
	    }
	 
	 public Section getSectionByCode(String sectionCode) {

	        Map<String, Object> params = new HashMap<>();
	        params.put("sec_cd", sectionCode);

	        return namedParameterJdbcTemplate.queryForObject(
	        	sectionQueries.getSectionByCode,
	            params,
	            (rs, rowNum) -> {
	                Section section = new Section();
	                section.setSectionCode(rs.getString("sec_cd"));
	                section.setSectionName(rs.getString("sec_name"));
	                section.setSectionShortName(rs.getString("sec_srt_nm"));
	                section.setSecAloMapCode(rs.getString("sec_aloc_map_cd"));
	                section.setDeleteFlag(rs.getString("del_flag"));
	                section.setCreatedDate(rs.getTimestamp("created_dt") != null ? rs.getTimestamp("created_dt").toLocalDateTime() : null);
	                section.setCreatedBy(rs.getString("created_by"));
	                section.setLastUpdatedDateTime(rs.getTimestamp("last_updated_dt") != null ? rs.getTimestamp("last_updated_dt").toLocalDateTime() : null);
	                section.setLastUpdatedBy(rs.getString("lat_updated_by"));
	                section.setOfficerInchargeCode(rs.getString("officer_incharge_cd"));
	                section.setOfficerInchargeName(rs.getString("officer_incharge_name"));
	                return section;
	            }
	        );
	    }
	
}
