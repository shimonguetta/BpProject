package com.example.demo.mapper;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public abstract  class ItemDecorator implements  ItemMapper{
    private ItemMapper mapper;

    private DateMapper dateMapper;

    @Autowired
    public void setDateMapper(DateMapper dateMapper) {
        this.dateMapper = dateMapper;
    }

    @Autowired
    public void setMapper(ItemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ItemDto itemToDtoItem(Item item) {
        ItemDto itemDto = mapper.itemToDtoItem(item);
        itemDto.setId(item.getId());
        itemDto.setCreatedDate(dateMapper.asOffsetDateTime(item.getCreatedDate()));
        itemDto.setLastModifiedDate(dateMapper.asOffsetDateTime(item.getLastModifiedDate()));
        itemDto.setItemType(item.getItemType());
        itemDto.setPrice(item.getPrice());
        itemDto.setItemName(item.getName());
        return itemDto;
    }

    @Override
    public Item itemDtoToItem(ItemDto itemDto) {
        System.out.println(itemDto);
        Item item = mapper.itemDtoToItem(itemDto);
        item.setId(itemDto.getId());
        //item.setCreatedDate(dateMapper.asTimestamp(itemDto.getCreatedDate()));
        //item.setLastModifiedDate(dateMapper.asTimestamp(itemDto.getLastModifiedDate()));
        item.setItemType(itemDto.getItemType());
        item.setPrice(itemDto.getPrice());
        item.setName(itemDto.getItemName());
        return item;
    }

}
