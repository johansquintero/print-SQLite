package com.connection.services;

import com.connection.dto.UserDTO;
import com.connection.entities.UserEntity;
import com.connection.repositories.IUserCrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserCrudRepository userCrudRepository;

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> users = (List<UserEntity>) this.userCrudRepository.findAll();
        return users.stream().map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getAge(), userEntity.getEmail())).toList();
    }

    @Override
    public Optional<UserDTO> save(UserDTO userDTO) {
        return Optional.of(this.userCrudRepository.save(UserEntity.builder()
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .build()))
                .map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getAge(), userEntity.getEmail()));
    }

    @Override
    public Optional<UserDTO> update(UserDTO userDTO) {
        Optional<UserEntity> userEntityOpt = this.userCrudRepository.findById(userDTO.getId());
        if (userEntityOpt.isEmpty()){
            throw new RuntimeException("The user doesn't exists");
        }
        return Optional.of(this.userCrudRepository.save(UserEntity.builder()
                        .id(userDTO.getId())
                        .age(userDTO.getAge())
                        .email(userDTO.getEmail())
                        .name(userDTO.getName())
                        .lastName(userDTO.getLastName())
                        .build()))
                .map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getAge(), userEntity.getEmail()));
    }

    @Override
    public String delete(Long id) {
        Optional<UserEntity> userEntityOpt = this.userCrudRepository.findById(id);
        if (userEntityOpt.isEmpty()){
            throw new RuntimeException("The user doesn't exists");
        }
        this.userCrudRepository.deleteById(id);
        return "The user was deleted successfully";
    }
}
