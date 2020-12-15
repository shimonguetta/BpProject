package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectricityService implements UserService {
    private final ItemRepository itemRepository;
    @Override
    public void addItem(Item item) throws InvalidEntityException {
        if (item.getItemType() != ItemType.ELECTRICITY){
            throw new InvalidEntityException("cannot add an item outside your domain");
        }
        itemRepository.save(item);
    }

    @Override
    public void updateItem(Item item) throws InvalidEntityException {
        if (item.getItemType() != ItemType.ELECTRICITY){
            throw new InvalidEntityException("cannot update an item outside your domain");
        }
        if(itemRepository.findById(item.getId()).isEmpty()){
            throw new InvalidEntityException("Cannot update not existing id");
        }
        itemRepository.save(item);

    }

    @Override
    public void deleteItem(Item item) throws InvalidEntityException {
        if (item.getItemType() != ItemType.ELECTRICITY){
            throw new InvalidEntityException("cannot delete an item outside your domain");
        }
        itemRepository.delete(item);
    }

    @Override
    public Item getItem(Long id) throws InvalidEntityException {
        Optional<Item> item = itemRepository.findByIdAndItemType(id, ItemType.ELECTRICITY);
        if(item.isEmpty()){
            throw new InvalidEntityException("Item not found");
        }
        return item.get();
    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findByItemType(ItemType.ELECTRICITY);
    }

    @Override
    public Long getItemsCount() {
        return itemRepository.countItemsByType(ItemType.ELECTRICITY.name());
    }
}
