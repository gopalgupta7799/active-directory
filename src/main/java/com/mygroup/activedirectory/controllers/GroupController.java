package com.mygroup.activedirectory.controllers;

import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import com.mygroup.activedirectory.services.GroupService;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

  final GroupService groupService;

  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @GetMapping("")
  ResponseEntity<List<Group>> getGroups() {
    return new ResponseEntity<>(groupService.getGroups(), HttpStatus.OK);
  }

  @GetMapping("/{groupId}")
  ResponseEntity<Group> getGroup(@PathVariable String groupId) {
    return new ResponseEntity<>(groupService.getGroup(groupId), HttpStatus.OK);
  }

  @PostMapping("")
  ResponseEntity<Group> addGroup(@RequestBody Group group) {
    return new ResponseEntity<>(groupService.addGroup(group), HttpStatus.CREATED);
  }

  @DeleteMapping("/{groupId}")
  ResponseEntity<String> deleteGroup(@PathVariable String groupId) {
    groupService.deleteGroup(groupId);
    return new ResponseEntity<>("Deleted Group with Id: " + groupId, HttpStatus.OK);
  }

  @PutMapping("")
  ResponseEntity<Group> updateGroup(@RequestBody Group group) {
    return new ResponseEntity<>(groupService.updateGroup(group), HttpStatus.OK);
  }

  @GetMapping("/{groupId}/users")
  ResponseEntity<Set<User>> getGroupUsers(@PathVariable String groupId) {
    return new ResponseEntity<>(groupService.getGroupUsers(groupId), HttpStatus.OK);
  }

}