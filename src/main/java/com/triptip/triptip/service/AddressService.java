package com.triptip.triptip.service;

import com.triptip.triptip.model.Address;
import com.triptip.triptip.model.User;
import com.triptip.triptip.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//big change
@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    public void editAddress(Address newAddress){
        System.out.println(newAddress);
        Address address = addressRepository.findByAddressId(newAddress.getAddressId());
        address = new Address(newAddress);
        addressRepository.save(address);
    }
    public void editAddress2(int id, String country, String city, String street, int houseNo, int postal){
        Address address = addressRepository.findByAddressId(id);
        address = new Address(id,houseNo,street,country,city,postal);
        addressRepository.save(address);
    }
    public Address addAddress(Address address){
        return addressRepository.save(address);
    }

}
