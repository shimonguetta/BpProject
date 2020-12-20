package com.example.demo.bootstrap;

import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.service.AdminService;
import com.example.demo.utils.ArtUtils;
import com.example.demo.utils.TablePrinter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Component
@Order(15)
@RequiredArgsConstructor
public class AdminControllerTest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final AdminService adminService;
    @Override
    public void run(String... args) throws Exception {

        System.out.println(ArtUtils.SERVICE_TESTING);
         String res = restTemplate.getForObject("HTTP://localhost:8080/admin/items/all", String.class);
        System.out.println(res);

        ItemDto testItem =  ItemDto.builder()
                .itemType(ItemType.SPORTS)
                .itemName("item1")
                .price(BigDecimal.valueOf(102.2)).build();

        res = restTemplate.postForObject("HTTP://localhost:8080/admin/items",testItem,String.class);
        TablePrinter.print(adminService.getAllItem());


    }

}
