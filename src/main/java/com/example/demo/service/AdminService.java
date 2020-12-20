package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.repository.ItemRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service

public class AdminService extends  BasicUserDtoService implements UserService{

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
        Optional<Item> savedItem = itemRepository.findById(itemDto.getId());
        if(savedItem.isEmpty()){
            throw new InvalidEntityException("Cannot update not existing id");
        }
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
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new InvalidEntityException("Item not found");
        }
        return itemMapper.itemToDtoItem(item.get());

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
