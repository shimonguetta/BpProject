package com.example.demo.web.controllers;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @SneakyThrows
    @PostMapping("items")
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto){
            adminService.addItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @SneakyThrows
    @PutMapping("items")
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto){
            adminService.updateItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @SneakyThrows
    @DeleteMapping("items")
    public ResponseEntity<?> deleteItem(@RequestBody ItemDto itemDto){
            adminService.deleteItem(itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneItem(@PathVariable Long id){
            return new ResponseEntity<>(adminService.getItem(id),HttpStatus.OK);
    }
    @GetMapping("items/count")
    public ResponseEntity<?> countItems()
    {
        return new ResponseEntity<>(adminService.getItemsCount(),HttpStatus.OK);
    }
    @GetMapping("items/all")
    public ResponseEntity<?> getAllItems(){
        return new ResponseEntity<>(adminService.getAllItem(),HttpStatus.OK);
    }
}
