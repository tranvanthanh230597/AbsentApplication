package com.example.demo.ApiController;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class ApiRoleController {
    @Autowired
    RoleService roleService;
    //-------------------Retrieve All user--------------------------------------------------------//
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> listRole(){
        List<Role> RoleList = (List<Role>) roleService.findAll();
        if (RoleList.isEmpty()) {
            return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Role>>(RoleList, HttpStatus.OK);
    }
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable("id") long id) {
        System.out.println("Fetching role with id " + id);
        Role role = roleService.findById(id);
        if (role == null) {
            System.out.println("role with id " + id + " not found");
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    //-------------------Create a user--------------------------------------------------------
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public ResponseEntity<Role> createRole(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
        try {
        System.out.println("Creating role " + role.getRoleName());
        System.out.println("Creating role " + role.getRoleName());

        roleService.save(role);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //------------------- Update a user --------------------------------------------------------

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
    public HttpEntity<? extends Serializable> updateRole(@PathVariable("id") long id, @RequestBody Role role) {
        System.out.println("Updating Role " + id);
        Role currentRole = roleService.findById(id);
        if (currentRole == null) {
            System.out.println("Role with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentRole.setRoleName(role.getRoleName());
        currentRole.setId(role.getId());
        try {
            roleService.save(currentRole);
        }catch (Exception ex){
            return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Role>(currentRole, HttpStatus.OK);
    }
    //------------------- Delete a category --------------------------------------------------------

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Role> deleteRole(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting role with id " + id);

        Role role = roleService.findById(id);
        if (role == null) {
            System.out.println("Unable to delete. Role with id " + id + " not found");
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }

        roleService.remove(id);
        return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
    }
}
