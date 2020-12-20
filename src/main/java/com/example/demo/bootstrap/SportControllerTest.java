package com.example.demo.bootstrap;

import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.service.AdminService;
import com.example.demo.utils.AppArtUtils;
import com.example.demo.utils.TablePrinter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
@Component
@RequiredArgsConstructor
@Order(15)
public class SportControllerTest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final AdminService adminService;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args)  {
        String res = null;
        ItemDto itemDto;
        System.out.println(AppArtUtils.SPORT);


        try  {
             restTemplate.postForObject("HTTP://localhost:8080/sport/items", itemDtoGenerator(ItemType.SPORTS), String.class);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
            }
        TablePrinter.print(adminService.getAllItem());
        try  {
        restTemplate.postForObject("HTTP://localhost:8080/sport/items", itemDtoGenerator(ItemType.ELECTRICITY), String.class);
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