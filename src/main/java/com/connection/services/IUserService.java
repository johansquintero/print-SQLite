package com.connection.services;

import com.connection.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAll();

    Optional<UserDTO> save(UserDTO userDTO);

    Optional<UserDTO> update(UserDTO userDTO);

    String delete(Long id);
}
