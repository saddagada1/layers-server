package com.saivamsi.layers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/products")
@CrossOrigin("*")
public class ProductVersionController {

    @GetMapping
    public ResponseEntity<String> getProductVersion() {
        return new ResponseEntity<String>("hello admin", HttpStatus.OK);
    }
}
