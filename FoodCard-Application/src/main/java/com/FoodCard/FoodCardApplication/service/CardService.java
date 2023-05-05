package com.FoodCard.FoodCardApplication.service;

import com.FoodCard.FoodCardApplication.domain.Card;
import com.FoodCard.FoodCardApplication.domain.Menu;
import com.FoodCard.FoodCardApplication.repository.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CardService implements ICardService {
    ICardRepository iCardRepository;

    @Autowired
    public CardService(ICardRepository iCardRepository) {
        this.iCardRepository = iCardRepository;
    }

    @Override
    public Card addNewItem(Card card) {

        if (iCardRepository.findById(card.getUserEmail()).isEmpty()) {
            List<Menu> menu = card.getMenuList();
            for (Menu menu1 : menu) {
                menu1.setItemNumber(generateRandomNumber());
            }
            return iCardRepository.insert(card);
        }

        Card card1 = iCardRepository.findById(card.getUserEmail()).get();

        List<Menu> menuList = card1.getMenuList();

        List<Menu> getMenuList = card.getMenuList();

        for (Menu menu : getMenuList) {
            menu.setItemNumber(generateRandomNumber());
            menuList.add(menu);
        }
        card1.setMenuList(menuList);

        return iCardRepository.save(card1);
    }


    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    @Override
    public List<Menu> getCardsByUserEmail(String userEmail) {
        return iCardRepository.findById(userEmail).get().getMenuList();
    }

    @Override
    public boolean deleteById(int id, String userEmail) {
        Card card = iCardRepository.findById(userEmail).get();
        List<Menu> menuList = card.getMenuList();
        boolean removed = menuList.removeIf(menu -> menu.getItemNumber() == id);
        if (removed) {
            iCardRepository.save(card);
        }
        return removed;
    }


    @Override
    public int calculateTotalAmount(String userEmail) {
        int totalAmount =0 ;
        List<Menu> menuList = iCardRepository.findById(userEmail).get().getMenuList();
        for (Menu menu:menuList) {
            totalAmount = totalAmount + Integer.valueOf(menu.getItemPrice());
        }
        return totalAmount;
    }

    @Override
    public boolean deleteAllByEmailID(String userEmail) {
        Card card = iCardRepository.findById(userEmail).get();
        card.setMenuList(new ArrayList<>());
        iCardRepository.save(card);
        return true;
    }

}
