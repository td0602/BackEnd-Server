package com.rs.retailstore.repository;

import com.rs.retailstore.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //để Spring biết
public interface CustomerRepository extends CrudRepository<Customer, Integer> { //extend để sd lại các hàm save, fìnById có sẵn của Spring Data JPA
    //Tự tạo hàm
    List<Customer> findByUsername(String username);

}
