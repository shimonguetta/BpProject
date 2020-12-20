package com.example.demo.web.controllers;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.service.ElectricityService;
import com.example.demo.service.SportsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("electricity")
public class ElectricityController {
    private final ElectricityService electricityService;
    @SneakyThrows
    @PostMapping("items")
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto){
            electricityService.addItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @SneakyThrows
    @PutMapping("items")
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto){
            electricityService.updateItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @SneakyThrows
    @DeleteMapping("items")
    public ResponseEntity<?> deleteItem(@RequestBody ItemDto itemDto){
            electricityService.deleteItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneItem(@PathVariable Long id){
            return new ResponseEntity<>(electricityService.getItem(id),HttpStatus.OK);
    }
    @GetMapping("/items/count")
    public ResponseEntity<?> countItems()
    {
        return new ResponseEntity<>(electricityService.getItemsCount(),HttpStatus.OK);
    }
    @GetMapping("items/all")
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(electricityService.getAllItem(),HttpStatus.OK);
    }

}
