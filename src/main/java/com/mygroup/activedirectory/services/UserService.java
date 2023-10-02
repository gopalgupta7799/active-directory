package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import java.util.List;
import java.util.Set;

public interface UserService {

  List<User> getUsers();

  User getUser(String userId);

  User addUser(User user);

  void deleteUser(String userId);

  User updateUser(User user);

  void addUserToGroup(String userId, String groupId);

  Set<Group> getUserGroups(String userId);

  void removeUserFromGroup(String userId, String groupId);

}