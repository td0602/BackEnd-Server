package com.rs.retailstore.config;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired //Lấu dc Bean truy vấn
    private CustomerRepository customerRepository;

    @Autowired //để decode
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //xác thực cho provider
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<Customer> customers = customerRepository.findByUsername(username);

        if(CollectionUtils.isEmpty(customers)) {
            //Trưởng hợp không tìm dc customer có username nào như vậy
            throw new BadCredentialsException("No customer registered with this username= " + username);
        } else {
            //có tồn tại username đó và ta sẽ check password
            //password trong database đã encode và password login chưa encode
            if(passwordEncoder.matches(password, customers.get(0).getPassword())) {
                //Lấy role của user
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username, password,authorities);
            } else {
                throw new BadCredentialsException("Invalid password for username= " + username);
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
