package com.mygroup.activedirectory.dao;

import com.mygroup.activedirectory.entities.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupDao extends JpaRepository<Group, String> {

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM USERS_GROUPS WHERE GROUP_ID=:groupId", nativeQuery = true)
  void removeAssociations(@Param("groupId") String groupId);

}