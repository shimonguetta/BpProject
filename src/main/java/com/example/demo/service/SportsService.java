package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.exception.InvalidOperationException;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.repository.ItemRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportsService extends  BasicItemDtoService  implements BasicItemService {

    @Builder
    public SportsService(ItemRepository itemRepository, ItemMapper itemMapper) {
        super(itemRepository, itemMapper);
    }

    @Override
    public void addItem(ItemDto itemDto) throws InvalidEntityException , InvalidOperationException {
        if (itemDto.getItemType() != ItemType.SPORTS){
            throw new InvalidOperationException("cannot add an item outside your domain");
        }
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.save(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) throws InvalidEntityException ,InvalidOperationException{
        if (itemDto.getItemType() != ItemType.SPORTS){
            throw new InvalidOperationException("cannot update an item outside your domain");
        }
        Item savedItem = itemRepository.findById(itemDto.getId())
                .orElseThrow(()-> new InvalidEntityException("Cannot update not existing id"));
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.saveAndFlush(item);

    }

    @Override
    public void deleteItem(ItemDto itemDto) throws InvalidEntityException ,InvalidOperationException{
        if (itemDto.getItemType() != ItemType.SPORTS){
            throw new InvalidOperationException("cannot delete an item outside your domain");
        }
        Item item = itemMapper.itemDtoToItem(itemDto);
        itemRepository.delete(item);
    }

    @Override
    public ItemDto  getItem(Long id) throws InvalidEntityException {
        Item item = itemRepository.findByIdAndItemType(id, ItemType.SPORTS)
                .orElseThrow(()-> new InvalidEntityException("Item not found"));
        return itemMapper.itemToDtoItem(item);
    }

    @Override
    public List<ItemDto> getAllItem() {

        return itemMapper.itemsToDtos(itemRepository.findByItemType(ItemType.SPORTS));
    }

    @Override
    public Long getItemsCount() {
        return itemRepository.countItemsByType(ItemType.SPORTS.name());
    }
}
