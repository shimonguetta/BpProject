package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements UserService{
    private final ItemRepository itemRepository;
    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void updateItem(Item item) throws InvalidEntityException {
        if(itemRepository.findById(item.getId()).isEmpty()){
            throw new InvalidEntityException("Cannot update not existing id");
        }
        itemRepository.save(item);

    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);

    }

    @Override
    public Item getItem(Long id) throws InvalidEntityException {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new InvalidEntityException("Item not found");
        }
        return item.get();

    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Long getItemsCount() {
        return itemRepository.count();
    }
}
