package com.example.demo.web.controllers;

import com.example.demo.beans.Item;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;
    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item item){
            adminService.addItem(item);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody Item item){
        try {
            adminService.updateItem(item);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (InvalidEntityException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteItem(@RequestBody Item item){
            adminService.deleteItem(item);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneItem(@PathVariable Long id){
    try{
        return new ResponseEntity<>(adminService.getItem(id),HttpStatus.OK);
    }catch (InvalidEntityException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }
    @GetMapping
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(adminService.getAllItem(),HttpStatus.OK);
    }
}
