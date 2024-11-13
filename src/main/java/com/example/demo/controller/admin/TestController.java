package com.example.demo.controller.admin;

import com.example.demo.entity.UserEnitty;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/api/v1/test"))
public class TestController {

    @Autowired
    private UserRepository UserRepository;


    @GetMapping("/user")
    public List<UserEnitty> getAllUser() {
        return UserRepository.findAll();
    }
}
