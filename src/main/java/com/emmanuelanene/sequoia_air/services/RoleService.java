package com.emmanuelanene.sequoia_air.services;

import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.dtos.RoleDTO;

import java.util.List;

public interface RoleService {
    Response<?> createRole(RoleDTO roleDTO);
    Response<?> updateRole(RoleDTO roleDTO);
    Response<List<RoleDTO>> getAllRoles();
}
