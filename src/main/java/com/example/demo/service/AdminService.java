package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.repository.ItemRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AdminService extends  BasicItemDtoService implements BasicItemService {

    @Builder
    public AdminService(ItemRepository itemRepository, ItemMapper itemMapper) {
        super(itemRepository, itemMapper);
    }

    @Override
    public void addItem(ItemDto itemDto) {
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.save(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) throws InvalidEntityException {
        Item savedItem = itemRepository.findById(itemDto.getId())
                .orElseThrow(()-> new InvalidEntityException("Cannot update not existing id"));
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.saveAndFlush(item);

    }

    @Override
    public void deleteItem(ItemDto itemDto) {
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.delete(item);

    }

    @Override
    public ItemDto  getItem(Long id) throws InvalidEntityException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException("Item not found"));
        return itemMapper.itemToDtoItem(item);

    }

    @Override
    public List<ItemDto> getAllItem() {
        return itemMapper.itemsToDtos(itemRepository.findAll());
    }

    @Override
    public Long getItemsCount() {
        return itemRepository.count();
    }
}
