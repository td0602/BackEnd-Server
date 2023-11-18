package com.rs.retailstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration //anotation: fefine configs
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        UserDetails user = User.builder()
//                .username("trandoan1")
//                .password("$2a$10$n2VPm3XXNkRDGL0meVU7DeAAft5BgSvqYQhhCCLp61OdqERtu.O/C")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin1")
//                .password("$2a$10$n2VPm3XXNkRDGL0meVU7DeAAft5BgSvqYQhhCCLp61OdqERtu.O/C")
//                .roles("USER", "ADMIN")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }

    //Có hàm này --> password bát buộc phải encrypt
    @Bean
    public PasswordEncoder passwordEncoder(){ //tự đôgnj chuyển các thông tin user đang được mã hóa để lưu vào database
        return new BCryptPasswordEncoder();
    }

    //Mặc định Spring Security sẽ private tất cả các request --> cần gỡ private cho chức năng đăng ký
    @Bean //SecurityFilterChain sẽ filter request
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { //httpSecurity se chứa tất cả các thông tin request của chúng ta
        //add các config cho filter
        httpSecurity.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/v1/register").permitAll() //riêng API này cho all mọi người vào
                    .antMatchers("/v1/greeting").authenticated() //còn các API khác phải private
                .and().formLogin() //hiện form login với những API private
                .and().httpBasic();
        return httpSecurity.build();
    }
}

