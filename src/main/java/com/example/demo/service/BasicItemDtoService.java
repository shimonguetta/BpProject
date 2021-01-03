package com.example.demo.service;

import com.example.demo.mapper.ItemMapper;
import com.example.demo.repository.ItemRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BasicItemDtoService {
    protected final ItemRepository itemRepository;
    protected final ItemMapper itemMapper;

}
