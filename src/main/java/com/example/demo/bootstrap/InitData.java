package com.example.demo.bootstrap;

import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.service.AdminService;
import com.example.demo.service.ElectricityService;
import com.example.demo.service.SportsService;
import com.example.demo.utils.AppArtUtils;
import com.example.demo.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(5)
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final SportsService sportsService;
    private final ElectricityService electricityService;
    private final AdminService adminService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(AppArtUtils.BOOTSTRAP);

        electricityService.addItem( ItemDto.builder()
                .itemType(ItemType.ELECTRICITY)
                .itemName("TV")
                .price(BigDecimal.valueOf(100.1))
                .build());

        electricityService.addItem( ItemDto.builder()
                .itemType(ItemType.ELECTRICITY)
                .itemName("Oven")
                .price(BigDecimal.valueOf(100.1))
                .build());

        electricityService.addItem( ItemDto.builder()
                .itemType(ItemType.ELECTRICITY)
                .itemName("Refrigerator")
                .price(BigDecimal.valueOf(100.1))
                .build());

        electricityService.addItem( ItemDto.builder()
                .itemType(ItemType.ELECTRICITY)
                .itemName("Lamp")
                .price(BigDecimal.valueOf(100.1))
                .build());
        sportsService.addItem( ItemDto.builder()
                .itemType(ItemType.SPORTS)
                .itemName("Ball")
                .price(BigDecimal.valueOf(100.1))
                .build());
        sportsService.addItem( ItemDto.builder()
                .itemType(ItemType.SPORTS)
                .itemName("Bicycle")
                .price(BigDecimal.valueOf(100.1))
                .build());
        sportsService.addItem( ItemDto.builder()
                .itemType(ItemType.SPORTS)
                .itemName("Shoes")
                .price(BigDecimal.valueOf(100.1))
                .build());
        sportsService.addItem( ItemDto.builder()
                .itemType(ItemType.SPORTS)
                .itemName("Weights")
                .price(BigDecimal.valueOf(100.1))
                .build());
        adminService.addItem( ItemDto.builder()
                .itemType(ItemType.SPORTS)
                .itemName("Treadmill")
                .price(BigDecimal.valueOf(100.1))
                .build());
        adminService.addItem( ItemDto.builder()
                .itemType(ItemType.ELECTRICITY)
                .itemName("IPhone")
                .price(BigDecimal.valueOf(100.1))
                .build());
        TablePrinter.print(adminService.getAllItem());
    }

}
