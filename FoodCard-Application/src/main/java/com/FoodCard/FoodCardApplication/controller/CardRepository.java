package com.FoodCard.FoodCardApplication.controller;

import com.FoodCard.FoodCardApplication.domain.Card;
import com.FoodCard.FoodCardApplication.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/Card/")
public class CardRepository {
    private ICardService iCardService;
    @Autowired
    public CardRepository(ICardService iCardService) {
        this.iCardService = iCardService;
    }
    //http://localhost:7879/api/Card/addNewItem [POST]

    @PostMapping("/addNewItem")
    public ResponseEntity addNewItemIntoCard(HttpServletRequest request, @RequestBody Card card){
       String userEmail = (String) request.getAttribute("userEmail");
       if (userEmail!=null){
           card.setUserEmail(userEmail);
           return new ResponseEntity<>(iCardService.addNewItem(card), HttpStatus.ACCEPTED);
       }
       return null;
    }

    //http://localhost:7879/api/Card/getItemByEmail
    @GetMapping("/getItemByEmail")
    public ResponseEntity getItemByEmail(HttpServletRequest request){
        String userEmail = (String) request.getAttribute("userEmail");
        if (userEmail!=null){
            return new ResponseEntity<>(iCardService.getCardsByUserEmail(userEmail),HttpStatus.ACCEPTED);
        }
        return null;
    }

    //http://localhost:7879/api/Card/deleteItemByID/{itemNumber}
    @DeleteMapping("/deleteItemByID/{itemNumber}")
    public ResponseEntity  deleteItemByID(HttpServletRequest request,@PathVariable int itemNumber){
        String userEmail = (String) request.getAttribute("userEmail");
        if (userEmail!=null){
            return new ResponseEntity<>(iCardService.deleteById(itemNumber,userEmail),HttpStatus.OK);
        }
        return null;
    }
    //http://localhost:7879/api/Card/calculateTotalAmount
    @GetMapping("/calculateTotalAmount")
    public ResponseEntity calculateTotalAmount(HttpServletRequest request){
        String userEmail = (String) request.getAttribute("userEmail");
        if (userEmail!=null){
            System.out.println(iCardService.calculateTotalAmount(userEmail));
            return new ResponseEntity<>(iCardService.calculateTotalAmount(userEmail),HttpStatus.OK);
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
    //http://localhost:7879/api/Card/deleteAllCard
    @DeleteMapping("/deleteAllCard")
    public ResponseEntity deleteAllCards(HttpServletRequest request){
        String userEmail = (String) request.getAttribute("userEmail");
        System.out.println("================================================================================");
        System.out.println(userEmail);
        return new ResponseEntity<>(iCardService.deleteAllByEmailID(userEmail),HttpStatus.OK);
    }

}