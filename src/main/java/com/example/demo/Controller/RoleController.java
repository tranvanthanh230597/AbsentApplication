package com.example.demo.Controller;

import com.example.demo.Model.ClassJoin;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Service.AbsentApplicationService;
import com.example.demo.Service.ClassJoinService;
import com.example.demo.Service.RoleService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView ShowRoles(){
        Iterable<Role> roles = roleService.findAll();
        ModelAndView modelAndView =new ModelAndView("/Role/list");
        modelAndView.addObject("rolesList",roles);
        modelAndView.addObject("roleInfo",new Role());
        return modelAndView;
    }
    @RequestMapping(value = "/roles",method = RequestMethod.POST)
    public ModelAndView CreateRole(@ModelAttribute("roleInfo") Role role){
        roleService.save(role);
        Iterable<Role> roles = roleService.findAll();
        ModelAndView modelAndView =new ModelAndView("/Role/list");
        modelAndView.addObject("rolesList",roles);
        modelAndView.addObject("roleInfo",new Role());
        modelAndView.addObject("message", "New Role created successfully");
        return modelAndView;
    }
    @RequestMapping(value = "/editRole/{id}", method = RequestMethod.GET)
    public ModelAndView showEditRollForm(@PathVariable Long id){
        Role role = roleService.findById(id);
        if (role != null){
            ModelAndView modelAndView = new ModelAndView("Role/edit");
            modelAndView.addObject("roleInfo",role);
            return modelAndView;
        }
        else {
            Iterable<Role> roles = roleService.findAll();
            ModelAndView modelAndView =new ModelAndView("/Role/list");
            modelAndView.addObject("rolesList",roles);
            modelAndView.addObject("roleInfo",new Role());
            modelAndView.addObject("message", "Can not find Role with id: " + id);
            return modelAndView;
        }
    }
    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    public ModelAndView updateRole(@ModelAttribute("roleInfo")Role role){
        roleService.save(role);
        ModelAndView modelAndView =new ModelAndView("/Role/edit");
        modelAndView.addObject("roleInfo",role);
        modelAndView.addObject("message", "Update success");
        return modelAndView;
    }

    @RequestMapping(value = "/delRol/{id}", method = RequestMethod.GET)
    public ModelAndView showFormDeleteRole(@PathVariable Long id){
        Role role = roleService.findById(id);
        if (role != null){
            ModelAndView modelAndView = new ModelAndView("Role/delete");
            modelAndView.addObject("roleInfo",role);
            return modelAndView;
        }else {
            Iterable<Role> roles = roleService.findAll();
            ModelAndView modelAndView =new ModelAndView("/Role/list");
            modelAndView.addObject("rolesList",roles);
            modelAndView.addObject("roleInfo",new Role());
            modelAndView.addObject("message", "Can not find Role with id: " + id);
            return modelAndView;
        }
    }
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public String  deleteRole(@ModelAttribute("roleInfo") Role role){
        roleService.remove(role.getId());
        return "redirect:roles";
    }

}
