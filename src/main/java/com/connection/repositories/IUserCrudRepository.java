package com.connection.repositories;

import com.connection.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserCrudRepository extends CrudRepository<UserEntity, Long> {
}
