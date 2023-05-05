package com.Pizza.OrderApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Document
public class Order {
    @Id
    private int orderID;
    private String userEmail;
    private List<Menu> menuList;
    private int totalAmount;
}
