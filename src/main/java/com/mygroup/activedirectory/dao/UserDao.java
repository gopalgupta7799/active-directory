package com.mygroup.activedirectory.dao;

import com.mygroup.activedirectory.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

  @Transactional
  Page<User> findByGroupsId(String id, Pageable pageable);

}