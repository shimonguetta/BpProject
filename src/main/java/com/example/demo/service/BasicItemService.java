package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.exception.InvalidOperationException;

import java.util.List;

public interface BasicItemService {
    void addItem(ItemDto itemDto) throws InvalidEntityException , InvalidOperationException;
    void updateItem(ItemDto itemDto) throws InvalidEntityException, InvalidOperationException;
    void deleteItem(ItemDto itemDto) throws InvalidEntityException, InvalidOperationException;
    ItemDto  getItem(Long id) throws InvalidEntityException, InvalidOperationException;
    List<ItemDto> getAllItem();
    Long getItemsCount();
}
