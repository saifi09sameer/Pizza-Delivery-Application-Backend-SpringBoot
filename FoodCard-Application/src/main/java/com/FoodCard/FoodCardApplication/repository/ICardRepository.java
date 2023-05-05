package com.FoodCard.FoodCardApplication.repository;

import com.FoodCard.FoodCardApplication.domain.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICardRepository extends MongoRepository<Card,String> {
    List<Card> findByUserEmail(String userEmail);
    void deleteAllByUserEmail(String userEmail);
}
