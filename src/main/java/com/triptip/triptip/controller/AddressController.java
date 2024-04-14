package com.triptip.triptip.controller;

import com.triptip.triptip.model.Address;
import com.triptip.triptip.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PutMapping("/editAddress/{id}")
    public ResponseEntity <?> editAddress(@PathVariable int id, @RequestBody Address address){
        addressService.editAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body("Address updated successfully");

    }
    @PostMapping("/addAddress")
    public ResponseEntity<Map<String, Object>> addAddress(@RequestBody Address address) {
        Address addedAddress = addressService.addAddress(address);
        Map<String, Object> response = new HashMap<>();
        response.put("id", addedAddress.getAddressId());
        response.put("address", addedAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
