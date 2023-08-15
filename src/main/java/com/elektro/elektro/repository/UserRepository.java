package com.elektro.elektro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.elektro.elektro.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
