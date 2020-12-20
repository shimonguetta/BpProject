package com.example.demo.service;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.exception.InvalidEntityException;
import com.example.demo.exception.InvalidOperationException;
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
public class SportsService extends  BasicUserDtoService  implements UserService{

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
        item.setCreatedDate(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Jerusalem"))));
        item.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Jerusalem"))));
        itemRepository.save(item);
    }

    @Override
    public void updateItem(ItemDto itemDto) throws InvalidEntityException ,InvalidOperationException{
        if (itemDto.getItemType() != ItemType.SPORTS){
            throw new InvalidOperationException("cannot update an item outside your domain");
        }
        Optional<Item> savedItem = itemRepository.findById(itemDto.getId());
        if(savedItem.isEmpty()){
            throw new InvalidEntityException("Cannot update not existing id");
        }
        Item item = itemMapper.itemDtoToItem(itemDto);
        item.setCreatedDate(savedItem.get().getCreatedDate());
        item.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Jerusalem"))));
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
        Optional<Item> item = itemRepository.findByIdAndItemType(id, ItemType.SPORTS);
        if(item.isEmpty()){
            throw new InvalidEntityException("Item not found");
        }

        return itemMapper.itemToDtoItem(item.get());
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
