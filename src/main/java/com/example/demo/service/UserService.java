package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.exception.InvalidEntityException;

import java.util.List;

public interface UserService {
    void addItem(Item item) throws InvalidEntityException;
    void updateItem(Item item) throws InvalidEntityException;
    void deleteItem(Item item) throws InvalidEntityException;
    Item getItem(Long id) throws InvalidEntityException;
    List<Item> getAllItem();
    Long getItemsCount();
}
