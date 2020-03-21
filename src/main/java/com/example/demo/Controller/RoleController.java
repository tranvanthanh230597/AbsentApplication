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
@RequestMapping("/admin")
@Controller
public class RoleController extends AdminBaseController {
    private final  String TERM = "Roles ";

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView ShowRoles(){
        ModelAndView modelAndView =new ModelAndView("/Role/list");
        modelAndView.addObject("title",TITLE_ADD);
        modelAndView.addObject("term",TERM);
        modelAndView.addObject("userInfo",new User());
        return modelAndView;
    }

}
