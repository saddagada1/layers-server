package com.saivamsi.layers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/products")
@CrossOrigin("*")
public class ProductController {

    @GetMapping
    public ResponseEntity<String> getProduct() {
        return new ResponseEntity<String>("hello world", HttpStatus.OK);
    }
}
