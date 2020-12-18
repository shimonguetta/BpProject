package com.example.demo.mapper;

import com.example.demo.beans.Item;
import com.example.demo.dto.ItemDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {DateMapper.class}, componentModel = "spring")
@DecoratedWith(ItemDecorator.class)
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

   @Mappings({@Mapping(source = "name",target = "itemName"),
              @Mapping(source = "id",target = "Id"),})
    ItemDto itemToDtoItem(Item item);

   @Mappings({@Mapping(source = "itemName",target = "name"),
              @Mapping(source = "id",target = "id"),})
    Item itemDtoToItem(ItemDto itemDto);
    List<ItemDto> itemsToDtos(List<Item> items);
    List<Item> DtosToItems(List<ItemDto> itemDtos);

}
