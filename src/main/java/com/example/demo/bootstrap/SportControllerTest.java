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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Base64;

@Component
@RequiredArgsConstructor
@Order(15)
public class SportControllerTest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final AdminService adminService;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args)  {
        ItemDto itemDto;
        System.out.println(AppArtUtils.SPORT);
        String adminAuth = "sport:1234";
        String base64Cards = Base64.getEncoder().encodeToString(adminAuth.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Cards);


        try  {
             restTemplate.postForObject("HTTP://localhost:8080/sport/items",
                     new HttpEntity<ItemDto>(itemDtoGenerator(ItemType.SPORTS),headers), String.class);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
            }
        TablePrinter.print(adminService.getAllItem());
        try  {
        restTemplate.postForObject("HTTP://localhost:8080/sport/items",
                new HttpEntity<ItemDto>(itemDtoGenerator(ItemType.ELECTRICITY),headers), String.class);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        TablePrinter.print(adminService.getAllItem());

        try  {
            itemDto = restTemplate.exchange("HTTP://localhost:8080/sport/5" , HttpMethod.GET,
                    new HttpEntity<String>(headers),ItemDto.class).getBody();

            itemDto.setPrice(BigDecimal.valueOf(150));
            restTemplate.put("HTTP://localhost:8080/sport/items"
                    ,new HttpEntity<ItemDto>(itemDto,headers));

        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        try  {
            itemDto = restTemplate.exchange("HTTP://localhost:8080/sport/4" , HttpMethod.GET,
                    new HttpEntity<String>(headers),ItemDto.class).getBody();

            itemDto.setPrice(BigDecimal.valueOf(150));
            restTemplate.put("HTTP://localhost:8080/sport/items"
                    ,new HttpEntity<ItemDto>(itemDto,headers));
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