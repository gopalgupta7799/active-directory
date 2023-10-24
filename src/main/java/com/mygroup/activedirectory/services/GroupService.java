package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import org.springframework.data.domain.Page;

public interface GroupService {

  Page<Group> getGroups(int pageNumber);

  Group getGroup(String groupId);

  Group addGroup(Group group);

  void deleteGroup(String groupId);

  Group updateGroup(Group group);

  Page<User> getGroupUsers(String groupId, int pageNumber);

}