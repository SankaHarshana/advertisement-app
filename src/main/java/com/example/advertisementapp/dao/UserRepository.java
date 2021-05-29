package com.example.advertisementapp.dao;

import com.example.advertisementapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("SELECT u" +
            " FROM User u" +
            " WHERE u.id = :id ")
    User getUserById(@Param("id") long id);

    @Query("SELECT u" +
            " FROM User u" +
            " WHERE u.email = :email ")
    User getUserByEmail(@Param("email") String email);


}
