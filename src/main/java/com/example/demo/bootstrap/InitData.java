package com.example.demo.bootstrap;

import com.example.demo.beans.Item;
import com.example.demo.beans.ItemType;
import com.example.demo.service.AdminService;
import com.example.demo.service.ElectricityService;
import com.example.demo.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Order(10)
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final SportsService sportsService;
    private final ElectricityService electricityService;
    private final AdminService adminService;
    @Override
    public void run(String... args) throws Exception {
        electricityService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.ELECTRICITY)
                .name("TV")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());

        electricityService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.ELECTRICITY)
                .name("Oven")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());

        electricityService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.ELECTRICITY)
                .name("Refrigerator")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());

        electricityService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.ELECTRICITY)
                .name("Lamp")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        sportsService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.SPORTS)
                .name("Ball")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        sportsService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.SPORTS)
                .name("Bicycle")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        sportsService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.SPORTS)
                .name("Shoes")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        sportsService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.SPORTS)
                .name("Weights")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        adminService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.SPORTS)
                .name("Treadmill")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        adminService.addItem( Item.builder()
                .id(null)
                .itemType(ItemType.ELECTRICITY)
                .name("IPhone")
                .price(BigDecimal.valueOf(100.1))
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .lastModifiedDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());
    }
}
