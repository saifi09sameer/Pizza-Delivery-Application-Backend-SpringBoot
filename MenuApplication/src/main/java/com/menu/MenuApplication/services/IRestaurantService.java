package com.menu.MenuApplication.services;

import com.menu.MenuApplication.domain.Restaurant;
import com.menu.MenuApplication.exception.RestaurantAlreadyException;
import com.menu.MenuApplication.exception.RestaurantNotFoundException;

import java.util.List;

public interface IRestaurantService {
    Restaurant addNewRestaurant(Restaurant restaurant) throws RestaurantAlreadyException;
    Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;

    void deleteItemByItemNumber(int restaurantId,int itemNumber);
    List<Restaurant> getRestaurant();
}
