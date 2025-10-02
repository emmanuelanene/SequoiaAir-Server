package com.emmanuelanene.sequoia_air.security;

import com.emmanuelanene.sequoia_air.entities.User;
import com.emmanuelanene.sequoia_air.exceptions.NotFoundException;
import com.emmanuelanene.sequoia_air.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username).orElseThrow(()-> new NotFoundException("User Not Found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
