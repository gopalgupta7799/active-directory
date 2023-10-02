package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import java.util.List;
import java.util.Set;

public interface GroupService {

  List<Group> getGroups();

  Group getGroup(String groupId);

  Group addGroup(Group group);

  void deleteGroup(String groupId);

  Group updateGroup(Group group);

  Set<User> getGroupUsers(String groupId);

}