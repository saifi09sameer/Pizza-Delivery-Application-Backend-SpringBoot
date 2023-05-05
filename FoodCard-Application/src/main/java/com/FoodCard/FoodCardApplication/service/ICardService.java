package com.FoodCard.FoodCardApplication.service;

import com.FoodCard.FoodCardApplication.domain.Card;
import com.FoodCard.FoodCardApplication.domain.Menu;

import java.util.List;

public interface ICardService {
    Card addNewItem(Card card);
    List<Menu> getCardsByUserEmail(String userEmail);

    boolean deleteById(int id,String userEmail);
    int calculateTotalAmount(String userEmail);
    boolean deleteAllByEmailID(String userEmail);
}
