package com.ari.demo.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ari.demo.userservice.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
