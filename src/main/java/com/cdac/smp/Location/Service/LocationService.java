package com.cdac.smp.Location.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.smp.Location.Dao.LocationDao;
import com.cdac.smp.Location.Model.Location;
@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationDao locationDao;

   
    public int addLocation(Location location) {
       
        if (location.getLoc_srt_nm() != null && location.getLoc_srt_nm().length() > 3) {
            throw new IllegalArgumentException("loc_srt_nm must be max 3 characters");
        }
        return locationDao.insertLocation1(location);
    }

   
    public List<Location> viewAllLocations() {
        return locationDao.getAllLocations1();
    }

   
    public int updateLocation(Location location) {
        return locationDao.updateLocation1(location);
    }

    
    public int deleteLocation(String loc_cd) {
        return locationDao.softDeleteLocation(loc_cd);
    }
}
