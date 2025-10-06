package com.cdac.smp.Location.Dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.smp.Location.Model.Location;
import com.cdac.smp.Location.Queries.LocationQueries;
@Repository
public class LocationDao implements LocationQueries {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LocationDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    private RowMapper<Location> locationRowMapper() {
        return (rs, rowNum) -> {
            Location loc = new Location();
            loc.setLoc_cd(rs.getString("loc_cd"));
            loc.setLoc_name(rs.getString("loc_name"));
            loc.setLoc_srt_nm(rs.getString("loc_srt_nm"));
            loc.setSec_cd(rs.getString("sec_cd"));
            loc.setAloc_map_cd(rs.getString("aloc_map_cd"));
            loc.setStatus(rs.getString("status"));
            loc.setCreated_dt(rs.getTimestamp("created_dt") != null 
                ? rs.getTimestamp("created_dt").toLocalDateTime() : null);
            loc.setCreated_by(rs.getString("created_by"));
            loc.setLast_updated_dt(rs.getTimestamp("last_updated_dt") != null 
                ? rs.getTimestamp("last_updated_dt").toLocalDateTime() : null);
            loc.setLast_updated_by(rs.getString("last_updated_by"));
            loc.setMeta_status(rs.getString("meta_status"));
            loc.setMeta_remarks(rs.getString("meta_remarks"));
            return loc;
        };
    }
        private String truncate(String value, int length) {
            if (value != null && value.length() > length) {
                return value.substring(0, length);
            }
            return value;
        }

        private MapSqlParameterSource mapLocationParams(Location location) {
            LocalDateTime now = LocalDateTime.now();

          
            String createdBy = location.getCreated_by() != null && !location.getCreated_by().isEmpty() 
                               ? location.getCreated_by() : "SYSTEM";

            String lastUpdatedBy = location.getLast_updated_by() != null && !location.getLast_updated_by().isEmpty() 
                                   ? location.getLast_updated_by() : "SYSTEM";

            String metaRemarks = location.getMeta_remarks() != null && !location.getMeta_remarks().isEmpty() 
                                 ? location.getMeta_remarks() : "N/A";

            return new MapSqlParameterSource()
                    .addValue("loc_cd", location.getLoc_cd())
                    .addValue("loc_name", location.getLoc_name())
                    .addValue("loc_srt_nm", location.getLoc_srt_nm())
                    .addValue("sec_cd", location.getSec_cd())
                    .addValue("aloc_map_cd", location.getAloc_map_cd())
                    .addValue("status", location.getStatus())
                    .addValue("created_dt", Timestamp.valueOf(location.getCreated_dt() != null ? location.getCreated_dt() : now))
                    .addValue("created_by", createdBy)
                    .addValue("last_updated_dt", Timestamp.valueOf(location.getLast_updated_dt() != null ? location.getLast_updated_dt() : now))
                    .addValue("last_updated_by", lastUpdatedBy)
                    .addValue("meta_status", location.getMeta_status())
                    .addValue("meta_remarks", metaRemarks);
        }

    public int insertLocation1(Location location) {
        try {
            return namedParameterJdbcTemplate.update(INSERT_LOCATION, mapLocationParams(location));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Location> getAllLocations1() {
        try {
            return namedParameterJdbcTemplate.query(VIEW_LOCATION, locationRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return List.of(); 
        }
    }
    
    public Location getLocationByCode(String loc_cd) {
        try {
            String sql = "SELECT * FROM ems_prop_loc_mst WHERE loc_cd = :loc_cd";
            return namedParameterJdbcTemplate.queryForObject(
                    sql,
                    new MapSqlParameterSource("loc_cd", loc_cd),
                    locationRowMapper()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public int updateLocation1(Location location) {
        try {
            return namedParameterJdbcTemplate.update(UPDATE_LOCATION, mapLocationParams(location));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int softDeleteLocation(String loc_cd) {
        try {
            return namedParameterJdbcTemplate.update(
                    SOFT_DELETE_LOCATION,
                    new MapSqlParameterSource().addValue("loc_cd", loc_cd)
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
