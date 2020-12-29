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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Base64;

@Component
@Order(10)
@RequiredArgsConstructor
public class AdminControllerTest implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final AdminService adminService;
    @Override
    public void run(String... args)  {

        System.out.println(AppArtUtils.ADMIN);
        ItemDto testItem =  ItemDto.builder()
                .itemType(ItemType.SPORTS)
                .itemName("item1")
                .price(BigDecimal.valueOf(102.2)).build();

        String adminAuth = "admin:1234";
        String base64Cards = Base64.getEncoder().encodeToString(adminAuth.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Cards);

        restTemplate.postForObject("HTTP://localhost:8080/admin/items",
                new HttpEntity<>(testItem,headers),
                String.class);

        TablePrinter.print(adminService.getAllItem());


    }

}
