package com.triptip.triptip.service;

import com.triptip.triptip.model.Address;
import com.triptip.triptip.model.User;
import com.triptip.triptip.repository.AddressRepository;
import com.triptip.triptip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public AddressRepository addressRepository;
    public User getUser(String name){
        return userRepository.findByLogin(name);
    }
    public User getUserById(int id){return userRepository.findById(id);}
    public User addUser(User user){
        User exist = userRepository.findByLogin(user.getLogin());
        return exist == null ?  userRepository.save(user) : null;
    }
    public User login(String login, String password){
        User user = userRepository.findByLogin(login);
        if(user.getPassword().equals(password))return user;
        return null;
    }

    public void editDetails(int id, String fname, String lname, String email, int phone){
        User user = userRepository.findById(id);
        if(user == null) return;
        user.setEmail(email);
        user.setFname(fname);
        user.setLname(lname);
        user.setPhone(phone);
        userRepository.save(user);
    }
    public void editUserAddress(int userId, int addressId){
        User user = userRepository.findById(userId);
        if(user == null) return;

        Address address = addressRepository.findByAddressId(addressId);
        System.out.println(address);
        if(address==null)return ;
        user.setAddress(address);
        userRepository.save(user);
    }
    public void editUserAddress2(int userId, Address temp){
        System.out.println(temp);
        if(temp==null)return ;
        User user = userRepository.findById(userId);
        if(user == null) return;

        Address address = addressRepository.findByAddressId(temp.getAddressId());
//        System.out.println(address);
//        System.out.println(address);
        if(address==null)return ;
        user.setAddress(address);
        userRepository.save(user);
    }

}
