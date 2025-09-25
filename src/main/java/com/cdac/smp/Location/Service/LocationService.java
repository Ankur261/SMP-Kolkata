package com.cdac.smp.Location.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.smp.Location.Model.Location;

@Service
@Transactional

public class LocationService {
	@Autowired
	private Location loc;
	
	
	

}
