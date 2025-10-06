package com.cdac.smp.section.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.smp.section.dao.AddressDao;
import com.cdac.smp.section.model.Address;

@Service
public class AddressService {
	AddressDao addressDao ;
	
	public AddressService(AddressDao addressDao) {
		this.addressDao = addressDao ;
	}
	
	public List<Address> getAllAddress() {
		
		return addressDao.getAllAddress();
	}
}
