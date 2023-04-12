package com.example.cqrs.Repository;

import com.example.cqrs.Models.Entitys.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUserByName(String name);
}
