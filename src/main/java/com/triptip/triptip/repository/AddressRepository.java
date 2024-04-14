package com.triptip.triptip.repository;

import com.triptip.triptip.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByAddressId(int id);
    List <Address> findAll();
}
