package com.menu.MenuApplication.services;

import com.menu.MenuApplication.domain.Menu;
import com.menu.MenuApplication.domain.Restaurant;
import com.menu.MenuApplication.exception.RestaurantAlreadyException;
import com.menu.MenuApplication.exception.RestaurantNotFoundException;
import com.menu.MenuApplication.repository.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RestaurantService implements IRestaurantService{
    IRestaurantRepository iRestaurantRepository;
    @Autowired
    public RestaurantService(IRestaurantRepository iRestaurantRepository) {
        this.iRestaurantRepository = iRestaurantRepository;
    }

    @Override
    public Restaurant addNewRestaurant(Restaurant restaurant) throws RestaurantAlreadyException {
        if (iRestaurantRepository.findById(restaurant.getRestaurantID()).isEmpty()){
            return iRestaurantRepository.insert(restaurant);
        }
        throw new RestaurantAlreadyException();
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        if (iRestaurantRepository.findById(restaurant.getRestaurantID()).isEmpty()){
            throw new RestaurantNotFoundException();
        }
        List<Menu> updatedmenuList = iRestaurantRepository.findById(restaurant.getRestaurantID()).get().getMenuList();
        for (Menu menu:restaurant.getMenuList()) {
            menu.setItemNumber(generateRandomNumber());
            updatedmenuList.add(menu);
        }
        restaurant.setMenuList(updatedmenuList);
        return iRestaurantRepository.save(restaurant);
    }
    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
    @Override
    public void deleteItemByItemNumber(int restaurantId, int itemNumber) {
        Optional<Restaurant> optionalRestaurant = iRestaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            List<Menu> menuList = restaurant.getMenuList();
            for (int i = 0; i < menuList.size(); i++) {
                if (menuList.get(i).getItemNumber() == itemNumber) {
                    menuList.remove(i);
                    restaurant.setMenuList(menuList);
                    iRestaurantRepository.save(restaurant);
                    break;
                }
            }
        }
    }

    @Override
    public List<Restaurant> getRestaurant() {
        return iRestaurantRepository.findAll();
    }
}
