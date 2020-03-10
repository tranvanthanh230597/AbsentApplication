package com.example.demo.ApiController;

import com.example.demo.Model.AbsentApplication;
import com.example.demo.Service.AbsentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/rest")

public class ApiAbsentApplicationController {
    @Autowired
    private AbsentApplicationService absentApplicationService;
    //-------------------Retrieve All user--------------------------------------------------------//
    @RequestMapping(value = "/absent", method = RequestMethod.GET)
    public ResponseEntity<List<AbsentApplication>> listApp(){
        List<AbsentApplication> applicationList = (List<AbsentApplication>) absentApplicationService.findAll();
        if (applicationList.isEmpty()) {
            return new ResponseEntity<List<AbsentApplication>>(HttpStatus.NO_CONTENT);
            //You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<AbsentApplication>>(applicationList, HttpStatus.OK);
    }
    @RequestMapping(value = "/absent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbsentApplication> getApp(@PathVariable("id") long id) {
        System.out.println("Fetching application with id " + id);
        AbsentApplication application = absentApplicationService.findById(id);
        if (application == null) {
            System.out.println("application with id " + id + " not found");
            return new ResponseEntity<AbsentApplication>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AbsentApplication>(application, HttpStatus.OK);
    }

    //-------------------Create a user--------------------------------------------------------
    @RequestMapping(value = "/absent", method = RequestMethod.POST)
    public ResponseEntity<Void> createRole(@RequestBody AbsentApplication application, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Application with id " + application.getId());
        System.out.println("Creating Application with id " + application.getId());
        absentApplicationService.save(application);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/absent/{id}").buildAndExpand(application.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a user --------------------------------------------------------

    @RequestMapping(value = "/absent/{id}", method = RequestMethod.PUT)
    public HttpEntity<? extends Serializable> updateApplication(@PathVariable("id") long id, @RequestBody AbsentApplication application) {
        System.out.println("Updating application " + id);
        AbsentApplication currentApp = absentApplicationService.findById(id);
        if (currentApp == null) {
            System.out.println("App with id " + id + " not found");
            return new ResponseEntity<AbsentApplication>(HttpStatus.NOT_FOUND);
        }
        currentApp.setApplicationStatus(application.getApplicationStatus());
        currentApp.setLeaveOfAbsent(application.getLeaveOfAbsent());
        currentApp.setReason(application.getReason());
        currentApp.setStartDay(application.getStartDay());
        currentApp.setId(application.getId());
        return new ResponseEntity<AbsentApplication>(currentApp, HttpStatus.OK);
    }
    //------------------- Delete a category --------------------------------------------------------

    @RequestMapping(value = "/absent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AbsentApplication> deleteRole(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting app with id " + id);

        AbsentApplication application = absentApplicationService.findById(id);
        if (application == null) {
            System.out.println("Unable to delete. application with id " + id + " not found");
            return new ResponseEntity<AbsentApplication>(HttpStatus.NOT_FOUND);
        }

        absentApplicationService.remove(id);
        return new ResponseEntity<AbsentApplication>(HttpStatus.NO_CONTENT);
    }
}
