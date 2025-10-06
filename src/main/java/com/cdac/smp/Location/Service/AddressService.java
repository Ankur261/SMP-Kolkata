package com.cdac.smp.Location.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.smp.Location.Dao.AddressDao;
import com.cdac.smp.Location.Model.Address;


@Service
@Transactional
public class AddressService {
	@Autowired 
	private AddressDao addressDao;
	
	  public List<Address> viewAllAddress() {
	        return addressDao.getAllAddresses();
	    }

	    public Address getLocationByCode(String address) {
	        return addressDao.getAddressByCode(address);
	    }
}
