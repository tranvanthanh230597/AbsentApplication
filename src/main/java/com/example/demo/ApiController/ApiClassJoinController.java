package com.example.demo.ApiController;

import com.example.demo.Model.ClassJoin;
import com.example.demo.Model.User;
import com.example.demo.Service.ClassJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ApiClassJoinController {
    @Autowired
    ClassJoinService classJoinService;

    //-------------------Retrieve All ClassJoin--------------------------------------------------------//
    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public ResponseEntity<List<ClassJoin>> listClass(){
        List<ClassJoin> classJoinList = (List<ClassJoin>) classJoinService.findAll();
        if (classJoinList.isEmpty()) {
            return new ResponseEntity<List<ClassJoin>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ClassJoin>>(classJoinList, HttpStatus.OK);
    }
    @RequestMapping(value = "/class/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClassJoin> getClass(@PathVariable("id") long id) {
        System.out.println("Fetching class with id " + id);
        ClassJoin classJoin = classJoinService.findById(id);
        if (classJoin == null) {
            System.out.println("class with id " + id + " not found");
            return new ResponseEntity<ClassJoin>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ClassJoin>(classJoin, HttpStatus.OK);
    }

    //-------------------Create a class--------------------------------------------------------
    @RequestMapping(value = "/class", method = RequestMethod.POST)
    public ResponseEntity<Void> createClass(@RequestBody ClassJoin classJoin, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating class " + classJoin.getClassName());
        System.out.println("Creating class " + classJoin.getClassName());
        classJoinService.save(classJoin);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/class/{id}").buildAndExpand(classJoin.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a class --------------------------------------------------------

    @RequestMapping(value = "/class/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClassJoin> updateClass(@PathVariable("id") long id, @RequestBody ClassJoin classJoin) {
        System.out.println("Updating Class " + id);

        ClassJoin currentClass = classJoinService.findById(id);

        if (currentClass == null) {
            System.out.println("class with id " + id + " not found");
            return new ResponseEntity<ClassJoin>(HttpStatus.NOT_FOUND);
        }

        currentClass.setClassName(classJoin.getClassName());
        currentClass.setId(classJoin.getId());

        classJoinService.save(currentClass);
        return new ResponseEntity<ClassJoin>(currentClass, HttpStatus.OK);
    }
    //------------------- Delete a class --------------------------------------------------------

    @RequestMapping(value = "/class/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ClassJoin> deleteClass(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting class with id " + id);

        ClassJoin classJoin = classJoinService.findById(id);
        if (classJoin == null) {
            System.out.println("Unable to delete. classJoin with id " + id + " not found");
            return new ResponseEntity<ClassJoin>(HttpStatus.NOT_FOUND);
        }

        classJoinService.remove(id);
        return new ResponseEntity<ClassJoin>(HttpStatus.NO_CONTENT);
    }
}
