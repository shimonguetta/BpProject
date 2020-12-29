package com.example.demo.bootstrap;

import com.example.demo.beans.ItemType;
import com.example.demo.dto.ItemDto;
import com.example.demo.service.AdminService;
import com.example.demo.utils.AppArtUtils;
import com.example.demo.utils.TablePrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Base64;

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
        String adminAuth = "electricity:1234";
        String base64Cards = Base64.getEncoder().encodeToString(adminAuth.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Cards);

        try  {
            restTemplate.postForObject("HTTP://localhost:8080/electricity/items"
                    ,new HttpEntity<>(itemDtoGenerator(ItemType.SPORTS),headers), String.class);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        TablePrinter.print(adminService.getAllItem());
        try  {
            restTemplate.postForObject("HTTP://localhost:8080/electricity/items"
                    ,new HttpEntity<>(itemDtoGenerator(ItemType.ELECTRICITY),headers), String.class);
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        TablePrinter.print(adminService.getAllItem());
        try  {
            itemDto = restTemplate.exchange("HTTP://localhost:8080/electricity/5" , HttpMethod.GET,
                new HttpEntity<String>(headers),ItemDto.class).getBody();
            itemDto.setPrice(BigDecimal.valueOf(150));
            restTemplate.put("HTTP://localhost:8080/electricity/items"
                    ,new HttpEntity<>(itemDto,headers));
        }catch ( HttpClientErrorException e){
            System.out.println(e.getResponseBodyAsString());
            System.out.println(e.getStatusCode());
        }
        try  {
            itemDto = restTemplate.exchange("HTTP://localhost:8080/electricity/4" , HttpMethod.GET,
                    new HttpEntity<String>(headers),ItemDto.class).getBody();
            itemDto.setPrice(BigDecimal.valueOf(150));
            restTemplate.put("HTTP://localhost:8080/electricity/items"
                    ,new HttpEntity<>(itemDto,headers));
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
