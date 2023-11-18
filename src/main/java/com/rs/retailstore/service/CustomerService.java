package com.rs.retailstore.service;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired //Lấy Bean của passwordEncoder để mã hóa password rồi mới lưu vào database
    PasswordEncoder passwordEncoder;


    @Autowired //để có thển gọi được @Bean của CustomerRepository lên
    CustomerRepository customerRepository;

    //dùng CustomerRepository để lưu thông tind đăng ký của customer xuống database
    public Customer createCustomer(Customer customer){
        //mã hóa password
        String password = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(password);
        return customerRepository.save(customer); //trả về Customer nếu nó save thành công
    }

}
