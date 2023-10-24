package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import org.springframework.data.domain.Page;

public interface UserService {

  Page<User> getUsers(int pageNumber);

  User getUser(String userId);

  User addUser(User user);

  void deleteUser(String userId);

  User updateUser(User user);

  void addUserToGroup(String userId, String groupId);

  Page<Group> getUserGroups(String userId, int pageNumber);

  void removeUserFromGroup(String userId, String groupId);

}