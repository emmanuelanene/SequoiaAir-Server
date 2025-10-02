package com.emmanuelanene.sequoia_air.services.impl;

import com.emmanuelanene.sequoia_air.dtos.LoginRequest;
import com.emmanuelanene.sequoia_air.dtos.LoginResponse;
import com.emmanuelanene.sequoia_air.dtos.RegistrationRequest;
import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.entities.Role;
import com.emmanuelanene.sequoia_air.entities.User;
import com.emmanuelanene.sequoia_air.enums.AuthMethod;
import com.emmanuelanene.sequoia_air.exceptions.BadRequestException;
import com.emmanuelanene.sequoia_air.exceptions.NotFoundException;
import com.emmanuelanene.sequoia_air.repo.RoleRepo;
import com.emmanuelanene.sequoia_air.repo.UserRepo;
import com.emmanuelanene.sequoia_air.security.JwtUtils;
import com.emmanuelanene.sequoia_air.services.AuthService;
import com.emmanuelanene.sequoia_air.services.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RoleRepo roleRepo;
    private final EmailNotificationService emailNotificationService;
    private final AuthenticationManager authenticationManager;


    @Override
    public Response<?> register(RegistrationRequest registrationRequest) {
        log.info("Inside register()");
        if (userRepo.existsByEmail(registrationRequest.getEmail())){
            throw new BadRequestException("Email already exist");
        }

        List<Role> userRoles;

        if (registrationRequest.getRoles() != null && !registrationRequest.getRoles().isEmpty()){
            userRoles = registrationRequest.getRoles().stream()
                    .map(roleName -> roleRepo.findByName(roleName.toUpperCase())
                            .orElseThrow(()-> new NotFoundException("Role " + roleName + "Not Found")))
                    .toList();
        }else{
            Role defaultRole = roleRepo.findByName("CUSTOMER")
                    .orElseThrow(()-> new NotFoundException("Role CUSTOMER DOESN'T EXISTS"));
            userRoles = List.of(defaultRole);
        }

        User userToSave = new User();
        userToSave.setName(registrationRequest.getName());
        userToSave.setEmail(registrationRequest.getEmail());
        userToSave.setPhoneNumber(registrationRequest.getPhoneNumber());
        userToSave.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        userToSave.setRoles(userRoles);
        userToSave.setCreatedAt(LocalDateTime.now());
        userToSave.setUpdatedAt(LocalDateTime.now());
        userToSave.setProvider(AuthMethod.LOCAL);
        userToSave.setActive(true);

        User savedUser = userRepo.save(userToSave);

        emailNotificationService.sendWelcomeEmail(savedUser);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("user registered sucessfully")
                .build();
    }

    @Override
    public Response<LoginResponse> login(LoginRequest loginRequest) {

        log.info("Inside login()");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        String token = jwtUtils.generateToken(userDetails.getUsername());

        List<String > roleNames = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setRoles(roleNames);

        return Response.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Login Successful")
                .data(loginResponse)
                .build();
    }
}








