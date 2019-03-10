package com.amrut.spring.controller;

import com.amrut.spring.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @RequestMapping(path = "/user",method = RequestMethod.GET)
    public String getUserInfo(){

        return "user";
    }
}
