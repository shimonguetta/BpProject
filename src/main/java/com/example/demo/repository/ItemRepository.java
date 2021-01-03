package com.example.demo.repository;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByItemType(ItemType itemType);
    Optional<Item> findByIdAndItemType(Long id , ItemType itemType);
    @Query(value = "SELECT COUNT(*) FROM items where item_type = :item_type",nativeQuery = true)
    Long countItemsByType(@Param("item_type") String item_type);

    @Query(value = "SELECT COUNT(*) FROM items",nativeQuery = true)
    Integer countAllItems();
}
