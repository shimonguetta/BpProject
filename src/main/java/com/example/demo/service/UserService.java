package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;

import java.util.List;

public interface UserService {
    void addItem(ItemDto itemDto) throws InvalidEntityException;
    void updateItem(ItemDto itemDto) throws InvalidEntityException;
    void deleteItem(ItemDto itemDto) throws InvalidEntityException;
    ItemDto  getItem(Long id) throws InvalidEntityException;
    List<ItemDto> getAllItem();
    Long getItemsCount();
}
