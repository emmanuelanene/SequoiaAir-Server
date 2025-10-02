package com.emmanuelanene.sequoia_air.controller;

import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.dtos.UserDTO;
import com.emmanuelanene.sequoia_air.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PutMapping
    public ResponseEntity<Response<?>> updateMyAccount(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateMyAccount(userDTO));
    }

    @GetMapping("/pilots")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PILOT')")
    public ResponseEntity<Response<List<UserDTO>>> getAllPilots(){
        return ResponseEntity.ok(userService.getAllPilots());
    }

    @GetMapping("/me")
    public ResponseEntity<Response<UserDTO>> getAccountDetails(){
        return ResponseEntity.ok(userService.getAccountDetails());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response<?>> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
