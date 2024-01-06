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
public class GroupServiceImpl implements GroupService {

  final GroupRepository groupRepository;

  final UserRepository userRepository;

  public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
    this.groupRepository = groupRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Page<Group> getGroups(int pageNumber) {
    return groupRepository.findAll(PageRequest.of(pageNumber, 10, Sort.by("name")));
  }

  @Override
  public Group getGroup(String groupId) {
    Optional<Group> optionalGroup = groupRepository.findById(groupId);
    if (optionalGroup.isPresent()) return optionalGroup.get();
    else throw new NoSuchElementException("No group found with Id: " + groupId);
  }

  @Override
  public Group addGroup(Group group) {
    return groupRepository.save(group);
  }

  @Override
  public void deleteGroup(String groupId) {
    Optional<Group> optionalGroup = groupRepository.findById(groupId);
    if (optionalGroup.isPresent()) {
      groupRepository.removeAssociations(groupId);
      groupRepository.deleteById(groupId);
    } else throw new NoSuchElementException("No group found with Id: " + groupId);
  }

  @Override
  public Group updateGroup(Group group) {
    Optional<Group> optionalGroup = groupRepository.findById(group.getId());
    if (optionalGroup.isPresent()) return groupRepository.save(group);
    else throw new NoSuchElementException("No group found with Id: " + group.getId());
  }

  @Override
  public Page<User> getGroupUsers(String groupId, int pageNumber) {
    Optional<Group> optionalGroup = groupRepository.findById(groupId);
    if (optionalGroup.isPresent()) return userRepository.findByGroupsId(groupId,
        PageRequest.of(pageNumber, 10, Sort.by("name")));
    else throw new NoSuchElementException("No group found with Id: " + groupId);
  }

}