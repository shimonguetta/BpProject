package com.example.demo.web.controllers;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.service.AdminService;
import com.example.demo.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("sport")
public class SportsController {
    private final SportsService sportsService;
    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto){
        try {
            sportsService.addItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidEntityException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto){
        try {
            sportsService.updateItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (InvalidEntityException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteItem(@RequestBody ItemDto itemDto){
        try {
            sportsService.deleteItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InvalidEntityException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneItem(@PathVariable Long id){
        try{
            return new ResponseEntity<>(sportsService.getItem(id),HttpStatus.OK);
        }catch (InvalidEntityException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(sportsService.getAllItem(),HttpStatus.OK);
    }

}
