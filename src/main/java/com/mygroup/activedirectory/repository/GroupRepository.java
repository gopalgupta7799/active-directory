package com.mygroup.activedirectory.repository;

import com.mygroup.activedirectory.entities.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, String> {

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM USERS_GROUPS WHERE GROUP_ID=:groupId", nativeQuery = true)
  void removeAssociations(@Param("groupId") String groupId);

  @Transactional
  Page<Group> findByUsersId(String id, Pageable pageable);

}