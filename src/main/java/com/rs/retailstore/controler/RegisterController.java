package com.rs.retailstore.controler;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //bao gồm cả @Controller và @Requestbody
//RestController đánh dấu controller của chúng ta là một cái Bean, và cái Bean này được khởi tạo trong Spring Context
@RequestMapping("/v1") //tạo version của API để sau này ta thay đổi không ảnh hươngr đến version cũ
public class RegisterController {

    @Autowired //để lấy được Bean của customerService
    CustomerService customerService;

    //phương thức đăng ký
    @PostMapping("/register") //sd phương thức Post để tạo customer
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
        //tạo response để trả về cho Client
        ResponseEntity<String> response = null;

        try {//sau khi lấy được thông tin đăng ký customer từ người dùng --> lưu xuống data base
            Customer saveCustomer = customerService.createCustomer(customer);
            if(saveCustomer.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Customer is created successfully for customer=" + customer.getUsername()); //show ra cái data khi response
            }
        } catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred from server with exception = " + exception);
        }
        return response;
    }
}
