package com.rs.retailstore.service;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//implements UserDetailsService là để custom lại UserDetailsService
@Service
public class RetailStoreUserDetails implements UserDetailsService {
    //Check xem trong database xem có username đó chưa
    @Autowired //Autowired vào service UserDetailsService này để có thể dùng CustomerRepository
    private CustomerRepository customerRepository;

    //Customlại hàm có sẵn
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findByUsername(username);
        String password = null;
        //mỗi phần tử sẽ chứa username, password, role của user
        List<GrantedAuthority> authorities = null;

        if(customers.isEmpty()){
            throw new UsernameNotFoundException("User details not found for username = " + username);
        }

        username = customers.get(0).getUsername();
        password = customers.get(0).getPassword();
        //1 user có thể có nhiểu role
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));

        return new User(username, password, authorities);
    }
}
