package com.example.demo.bootstrap;

import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.service.AdminService;
import com.example.demo.utils.AppArtUtils;
import com.example.demo.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
@Component
@RequiredArgsConstructor
@Order(20)

public class ElectricityControllerTest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final AdminService adminService;

    @Override
    public void run(String... args) {
        ItemDto itemDto;
        System.out.println(AppArtUtils.ELECTRICITY);

        try  {
            restTemplate.postForObject("HTTP://localhost:8080/electricity/items", itemDtoGenerator(ItemType.SPORTS), String.class);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        TablePrinter.print(adminService.getAllItem());
        try  {
            restTemplate.postForObject("HTTP://localhost:8080/electricity/items", itemDtoGenerator(ItemType.ELECTRICITY), String.class);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        TablePrinter.print(adminService.getAllItem());
        try  {
            itemDto = restTemplate.getForObject("HTTP://localhost:8080/electricity/5",ItemDto.class);
            itemDto.setPrice(BigDecimal.valueOf(150));
            restTemplate.put("HTTP://localhost:8080/electricity/items",itemDto);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        try  {
            itemDto = restTemplate.getForObject("HTTP://localhost:8080/electricity/4",ItemDto.class);
            itemDto.setPrice(BigDecimal.valueOf(150));
            restTemplate.put("HTTP://localhost:8080/electricity/items",itemDto);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        TablePrinter.print(adminService.getAllItem());

    }
    private ItemDto itemDtoGenerator(ItemType itemType){
        return   ItemDto.builder()
                .itemType(itemType)
                .itemName("item " + (int)(Math.random() * 100) )
                .price(BigDecimal.valueOf(Math.random() * 1000)).build();
    }
}
