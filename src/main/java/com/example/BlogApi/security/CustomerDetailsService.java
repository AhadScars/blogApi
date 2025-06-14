package com.example.BlogApi.security;

import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.entity.UserPrincipal;
import com.example.BlogApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = repository.findByUsername(username);

        if (user==null){
            throw new UsernameNotFoundException("username not found");
        }
        return new UserPrincipal(user);
    }
}
