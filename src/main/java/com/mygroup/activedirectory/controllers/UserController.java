package com.mygroup.activedirectory.controllers;

import com.mygroup.activedirectory.entities.Group;
import com.mygroup.activedirectory.entities.User;
import com.mygroup.activedirectory.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  ResponseEntity<Page<User>> getUsers(@RequestHeader String pageNumber) {
    return new ResponseEntity<>(userService.getUsers(Integer.parseInt(pageNumber)), HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  ResponseEntity<User> getUser(@PathVariable String userId) {
    return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
  }

  @PostMapping("")
  ResponseEntity<User> addUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
  }

  @DeleteMapping("/{userId}")
  ResponseEntity<String> deleteUser(@PathVariable String userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<>("Deleted User with Id: " + userId, HttpStatus.OK);
  }

  @PutMapping("")
  ResponseEntity<User> updateUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
  }

  @PostMapping("/{userId}/groups/{groupId}")
  ResponseEntity<String> addUserToGroup(@PathVariable String userId, @PathVariable String groupId) {
    userService.addUserToGroup(userId, groupId);
    return new ResponseEntity<>("Added User with Id: " + userId + " to Group with Id:" + groupId,
        HttpStatus.OK);
  }

  @GetMapping("/{userId}/groups")
  ResponseEntity<Page<Group>> getUserGroups(@PathVariable String userId,
                                            @RequestHeader String pageNumber) {
    return new ResponseEntity<>(userService.getUserGroups(userId, Integer.parseInt(pageNumber)),
        HttpStatus.OK);
  }

  @DeleteMapping("/{userId}/groups/{groupId}")
  ResponseEntity<String> removeUserFromGroup(@PathVariable String userId,
                                             @PathVariable String groupId) {
    userService.removeUserFromGroup(userId, groupId);
    return new ResponseEntity<>("Removed User with Id: " + userId + " from Group with Id:" + groupId,
        HttpStatus.OK);
  }

}