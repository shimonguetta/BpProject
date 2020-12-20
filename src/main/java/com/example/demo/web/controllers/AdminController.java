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

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @SneakyThrows
    @PostMapping("items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody ItemDto itemDto){
            adminService.addItem(itemDto);
    }
    @SneakyThrows
    @PutMapping("items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateItem(@RequestBody ItemDto itemDto){
            adminService.updateItem(itemDto);
            return  ResponseEntity.noContent().build();
    }
    @SneakyThrows
    @DeleteMapping("items")
    public ResponseEntity<?> deleteItem(@RequestBody @Valid ItemDto itemDto){
            adminService.deleteItem(itemDto);
        return  ResponseEntity.noContent().build();
    }
    @SneakyThrows
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOneItem(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getItem(id));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("items/count")
    public ResponseEntity<?> countItems()
    {
        return ResponseEntity.ok(adminService.getItemsCount());
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("items/all")
    public ResponseEntity<?> getAllItems(){
        return  ResponseEntity.ok(adminService.getAllItem());
    }
}
