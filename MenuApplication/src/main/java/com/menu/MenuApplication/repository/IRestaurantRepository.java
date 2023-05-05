package com.menu.MenuApplication.repository;

import com.menu.MenuApplication.domain.Menu;
import com.menu.MenuApplication.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRestaurantRepository extends MongoRepository<Restaurant,Integer> {
    void deleteMenuByMenuListItemNumber(int itemNumber);


}
