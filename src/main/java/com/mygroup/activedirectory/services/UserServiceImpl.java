package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.dao.GroupDao;
import com.mygroup.activedirectory.dao.UserDao;
import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  final UserDao userDao;

  final GroupDao groupDao;

  public UserServiceImpl(UserDao userDao, GroupDao groupDao) {
    this.userDao = userDao;
    this.groupDao = groupDao;
  }

  @Override
  public List<User> getUsers() {
    return userDao.findAll();
  }

  @Override
  public User getUser(String userId) {
    Optional<User> optionalUser = userDao.findById(userId);
    if (optionalUser.isPresent()) return optionalUser.get();
    else throw new NoSuchElementException("No user found with Id: " + userId);
  }

  @Override
  public User addUser(User user) {
    return userDao.save(user);
  }

  @Override
  public void deleteUser(String userId) {
    Optional<User> optionalUser = userDao.findById(userId);
    if (optionalUser.isPresent()) userDao.delete(optionalUser.get());
    else throw new NoSuchElementException("No user found with Id: " + userId);
  }

  @Override
  public User updateUser(User user) {
    Optional<User> optionalUser = userDao.findById(user.getId());
    if (optionalUser.isPresent()) return userDao.save(user);
    else throw new NoSuchElementException("No user found with Id: " + user.getId());
  }

  @Override
  public void addUserToGroup(String userId, String groupId) {
    Optional<User> optionalUser = userDao.findById(userId);
    Optional<Group> optionalGroup = groupDao.findById(groupId);
    if (optionalUser.isEmpty())
      throw new NoSuchElementException("No user found with Id: " + userId);
    else if (optionalGroup.isEmpty())
      throw new NoSuchElementException("No Group found with Id: " + userId);
    else {
      User user = optionalUser.get();
      Group group = optionalGroup.get();
      user.getGroups().add(group);
      userDao.save(user);
    }
  }

  @Override
  public Set<Group> getUserGroups(String userId) {
    Optional<User> optionalUser = userDao.findById(userId);
    if (optionalUser.isPresent()) return optionalUser.get().getGroups();
    else throw new NoSuchElementException("No user found with Id: " + userId);
  }

  @Override
  public void removeUserFromGroup(String userId, String groupId) {
    Optional<User> optionalUser = userDao.findById(userId);
    Optional<Group> optionalGroup = groupDao.findById(groupId);
    if (optionalUser.isEmpty())
      throw new NoSuchElementException("No user found with Id: " + userId);
    else if (optionalGroup.isEmpty())
      throw new NoSuchElementException("No Group found with Id: " + userId);
    else {
      User user = optionalUser.get();
      Group group = optionalGroup.get();
      user.getGroups().remove(group);
      userDao.save(user);
    }
  }

}