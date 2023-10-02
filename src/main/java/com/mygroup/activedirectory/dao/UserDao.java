package com.mygroup.activedirectory.dao;

import com.mygroup.activedirectory.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

}