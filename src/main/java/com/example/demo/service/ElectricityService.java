package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.repository.ItemRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ElectricityService extends  BasicUserDtoService  implements UserService {
    @Builder
    public ElectricityService(ItemRepository itemRepository, ItemMapper itemMapper) {
        super(itemRepository, itemMapper);
    }

    @Override
    public void addItem(ItemDto itemDto) throws InvalidEntityException {
        if (itemDto.getItemType() != ItemType.ELECTRICITY){
            throw new InvalidEntityException("cannot add an item outside your domain");
        }
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.save(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) throws InvalidEntityException {
        if (itemDto.getItemType() != ItemType.ELECTRICITY){
            throw new InvalidEntityException("cannot update an item outside your domain");
        }
        if(itemRepository.findById(itemDto.getId()).isEmpty()){
            throw new InvalidEntityException("Cannot update not existing id");
        }
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.save(item);

    }

    @Override
    public void deleteItem(ItemDto itemDto) throws InvalidEntityException {
        if (itemDto.getItemType() != ItemType.ELECTRICITY){
            throw new InvalidEntityException("cannot delete an item outside your domain");
        }
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.delete(item);
    }

    @Override
    public ItemDto getItem(Long id) throws InvalidEntityException {
        Optional<Item> item = itemRepository.findByIdAndItemType(id, ItemType.ELECTRICITY);
        if(item.isEmpty()){
            throw new InvalidEntityException("Item not found");
        }
        return itemMapper.itemToDtoItem(item.get());
    }

    @Override
    public List<ItemDto> getAllItem() {
        return itemMapper.itemsToDtos(itemRepository.findByItemType(ItemType.ELECTRICITY));
    }

    @Override
    public Long getItemsCount() {
        return itemRepository.countItemsByType(ItemType.ELECTRICITY.name());
    }
}
