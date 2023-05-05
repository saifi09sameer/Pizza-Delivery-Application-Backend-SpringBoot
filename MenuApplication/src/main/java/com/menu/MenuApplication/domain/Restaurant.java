package com.menu.MenuApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Restaurant {
    @Id
    private int restaurantID;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
    private List<Menu> menuList;
}
