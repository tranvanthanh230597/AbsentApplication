package com.example.demo.ApiController;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ApiUserController {
    @Autowired
    private UserService userService;
    //-------------------Retrieve All user--------------------------------------------------------//
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUser(){
        List<User> UserList = (List<User>) userService.findAll();
        if (UserList.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(UserList, HttpStatus.OK);
    }
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching blog with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("user with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-------------------Create a user--------------------------------------------------------
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        try {
            System.out.println("Creating User " + user.getFirstName() + " " +user.getLastName());
            System.out.println("Creating User " + user.getFirstName() + " " +user.getLastName());

            Role role = new Role();
            role.setId((long) 2);

            user.setDayOff(0);
            user.setUserStatus("On");
            user.setRole(role);
            userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //------------------- Update a user --------------------------------------------------------

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating user " + id);
        User currentUser = userService.findById(id);
        if (currentUser == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.setUserId(user.getUserId());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setBirthDay(user.getBirthDay());
        currentUser.setClassJoin(user.getClassJoin());
        currentUser.setId(user.getId());

        try {
            userService.save(currentUser);
            System.out.println("oke");
        }catch (Exception ex){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
    //------------------- Delete a category --------------------------------------------------------

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
