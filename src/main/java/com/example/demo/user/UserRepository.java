package com.example.demo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.UserInfo;
import com.example.demo.utils.Constant;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.id id, u.firstname firstname, u.lastname lastname, u.email email, u.password password, u.role role FROM "
            + Constant.SCHEMA
            + "._user u WHERE email = :email", nativeQuery = true)
    public User getUserInfo(@Param("email") String email);

}