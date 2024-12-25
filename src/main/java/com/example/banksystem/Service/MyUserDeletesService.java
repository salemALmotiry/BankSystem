package com.example.banksystem.Service;

import com.example.banksystem.Api.ApiException;
import com.example.banksystem.Model.MyUser;
import com.example.banksystem.Repostiry.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDeletesService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = authRepository.findMyUserByUsername(username);

        if (myUser == null) throw new ApiException("Wrong username or password");

        return myUser;

    }
}
