package com.mygroup.activedirectory.services;

import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import com.mygroup.activedirectory.repository.GroupRepository;
import com.mygroup.activedirectory.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  final UserRepository userRepository;

  final GroupRepository groupRepository;

  public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository) {
    this.userRepository = userRepository;
    this.groupRepository = groupRepository;
  }

  @Override
  public Page<User> getUsers(int pageNumber) {
    return userRepository.findAll(PageRequest.of(pageNumber, 10, Sort.by("name")));
  }

  @Override
  public User getUser(String userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) return optionalUser.get();
    else throw new NoSuchElementException("No user found with Id: " + userId);
  }

  @Override
  public User addUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public void deleteUser(String userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) userRepository.delete(optionalUser.get());
    else throw new NoSuchElementException("No user found with Id: " + userId);
  }

  @Override
  public User updateUser(User user) {
    Optional<User> optionalUser = userRepository.findById(user.getId());
    if (optionalUser.isPresent()) return userRepository.save(user);
    else throw new NoSuchElementException("No user found with Id: " + user.getId());
  }

  @Override
  public void addUserToGroup(String userId, String groupId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    Optional<Group> optionalGroup = groupRepository.findById(groupId);
    if (optionalUser.isEmpty())
      throw new NoSuchElementException("No user found with Id: " + userId);
    else if (optionalGroup.isEmpty())
      throw new NoSuchElementException("No Group found with Id: " + userId);
    else {
      User user = optionalUser.get();
      Group group = optionalGroup.get();
      user.getGroups().add(group);
      userRepository.save(user);
    }
  }

  @Override
  public Page<Group> getUserGroups(String userId, int pageNumber) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) return groupRepository.findByUsersId(userId,
        PageRequest.of(pageNumber, 10, Sort.by("name")));
    else throw new NoSuchElementException("No user found with Id: " + userId);
  }

  @Override
  public void removeUserFromGroup(String userId, String groupId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    Optional<Group> optionalGroup = groupRepository.findById(groupId);
    if (optionalUser.isEmpty())
      throw new NoSuchElementException("No user found with Id: " + userId);
    else if (optionalGroup.isEmpty())
      throw new NoSuchElementException("No Group found with Id: " + userId);
    else {
      User user = optionalUser.get();
      Group group = optionalGroup.get();
      user.getGroups().remove(group);
      userRepository.save(user);
    }
  }

}