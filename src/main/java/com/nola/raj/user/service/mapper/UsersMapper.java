package com.nola.raj.user.service.mapper;

import com.nola.raj.user.domain.*;
import com.nola.raj.user.service.dto.UsersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Users and its DTO UsersDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsersMapper extends EntityMapper<UsersDTO, Users> {



    default Users fromId(Long id) {
        if (id == null) {
            return null;
        }
        Users users = new Users();
        users.setId(id);
        return users;
    }
}
