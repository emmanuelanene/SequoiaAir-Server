package com.emmanuelanene.sequoia_air.services;

import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.dtos.UserDTO;
import com.emmanuelanene.sequoia_air.entities.User;

import java.util.List;

public interface UserService {

    User currentUser();

    Response<?> updateMyAccount(UserDTO userDTO);

    Response<List<UserDTO>> getAllPilots();

    Response<UserDTO> getAccountDetails();

    Response<?> deleteUser(Long userId);
}
