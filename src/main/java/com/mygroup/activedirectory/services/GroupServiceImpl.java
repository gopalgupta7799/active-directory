package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.dao.GroupDao;
import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

  @Autowired
  GroupDao groupDao;

  @Override
  public List<Group> getGroups() {
    return groupDao.findAll();
  }

  @Override
  public Group getGroup(String groupId) {
    Optional<Group> optionalGroup = groupDao.findById(groupId);
    if (optionalGroup.isPresent()) return optionalGroup.get();
    else throw new NoSuchElementException("No group found with Id: " + groupId);
  }

  @Override
  public Group addGroup(Group group) {
    return groupDao.save(group);
  }

  @Override
  public void deleteGroup(String groupId) {
    Optional<Group> optionalGroup = groupDao.findById(groupId);
    if (optionalGroup.isPresent()) {
      groupDao.removeAssociations(groupId);
      groupDao.deleteById(groupId);
    } else throw new NoSuchElementException("No group found with Id: " + groupId);
  }

  @Override
  public Group updateGroup(Group group) {
    Optional<Group> optionalGroup = groupDao.findById(group.getId());
    if (optionalGroup.isPresent()) return groupDao.save(group);
    else throw new NoSuchElementException("No group found with Id: " + group.getId());
  }

  @Override
  public Set<User> getGroupUsers(String groupId) {
    Optional<Group> optionalGroup = groupDao.findById(groupId);
    if (optionalGroup.isPresent()) return optionalGroup.get().getUsers();
    else throw new NoSuchElementException("No group found with Id: " + groupId);
  }

}