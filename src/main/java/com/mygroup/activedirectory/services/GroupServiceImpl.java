package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.dao.GroupDao;
import com.mygroup.activedirectory.dao.UserDao;
import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

  final GroupDao groupDao;

  final UserDao userDao;

  public GroupServiceImpl(GroupDao groupDao, UserDao userDao) {
    this.groupDao = groupDao;
    this.userDao = userDao;
  }

  @Override
  public Page<Group> getGroups(int pageNumber) {
    return groupDao.findAll(PageRequest.of(pageNumber, 10, Sort.by("name")));
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
  public Page<User> getGroupUsers(String groupId, int pageNumber) {
    Optional<Group> optionalGroup = groupDao.findById(groupId);
    if (optionalGroup.isPresent()) return userDao.findByGroupsId(groupId,
        PageRequest.of(pageNumber, 10, Sort.by("name")));
    else throw new NoSuchElementException("No group found with Id: " + groupId);
  }

}