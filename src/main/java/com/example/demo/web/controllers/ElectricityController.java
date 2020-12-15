package com.example.demo.web.controllers;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.service.ElectricityService;
import com.example.demo.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("electricity")
public class ElectricityController {
    private final ElectricityService electricityService;
    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto){
        try {
            electricityService.addItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidEntityException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto){
        try {
            electricityService.updateItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (InvalidEntityException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteItem(@RequestBody ItemDto itemDto){
        try {
            electricityService.deleteItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InvalidEntityException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneItem(@PathVariable Long id){
        try{
            return new ResponseEntity<>(electricityService.getItem(id),HttpStatus.OK);
        }catch (InvalidEntityException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(electricityService.getAllItem(),HttpStatus.OK);
    }

}
