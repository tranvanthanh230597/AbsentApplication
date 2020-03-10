package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView showUsers(){
        return new ModelAndView("/Users/list");
    }
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView ShowRoles(){
        return new ModelAndView("/Role/list");
    }
}
