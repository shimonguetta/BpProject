package com.example.demo.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
@GetMapping
    public ResponseEntity<?> sayHello(){
    return new ResponseEntity<>("Hello ", HttpStatus.OK);
}
}
