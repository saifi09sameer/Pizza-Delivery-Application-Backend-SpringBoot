package com.menu.MenuApplication.controller;

import com.menu.MenuApplication.domain.Restaurant;
import com.menu.MenuApplication.exception.RestaurantAlreadyException;
import com.menu.MenuApplication.exception.RestaurantNotFoundException;
import com.menu.MenuApplication.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/Restaurant/")
//@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {
    IRestaurantService iRestaurantService;

    @Autowired
    public RestaurantController(IRestaurantService iRestaurantService) {
        this.iRestaurantService = iRestaurantService;
    }

    //http://localhost:7868/api/Restaurant/addNewRestaurant
    @PostMapping("/addNewRestaurant")
    public ResponseEntity addNewRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyException {
        Restaurant restaurant1 = iRestaurantService.addNewRestaurant(restaurant);
        if (restaurant1 != null) {
            return new ResponseEntity<>(restaurant1, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Not Inserted", HttpStatus.BAD_REQUEST);
    }
    //http://localhost:7868/api/Restaurant/updateRestaurant
    @PutMapping("/updateRestaurant")
    public ResponseEntity updateRestaurant(HttpServletRequest request, @RequestBody Restaurant restaurant) throws RestaurantNotFoundException {
        String role = (String) request.getAttribute("role");
        System.out.println(role);
        if (role.equalsIgnoreCase("Admin")) {
            Restaurant restaurant1 = iRestaurantService.updateRestaurant(restaurant);
            if (restaurant1 != null) {
                return new ResponseEntity<>(restaurant1, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
    }
    //http://localhost:7868/api/Restaurant/getRestaurant
//    @GetMapping("/getRestaurant")
//    public ResponseEntity getListRestaurant(){
//        return new ResponseEntity<>(iRestaurantService.getRestaurant(),HttpStatus.OK);
//    }
    //http://localhost:7868/api/Restaurant/getRestaurant
    @GetMapping("/getRestaurant")
    public ResponseEntity getListRestaurant(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        if (userEmail != null) {
            return new ResponseEntity<>(iRestaurantService.getRestaurant(), HttpStatus.OK);
        }
        return new ResponseEntity<>("You Have Not Login ", HttpStatus.BAD_REQUEST);
    }

    //http://localhost:7868/api/Restaurant/deleteItemByAdmin/{itemNumber}
    @DeleteMapping("/deleteItemByAdmin/{itemNumber}")
    public ResponseEntity deleteItemByAdmin(HttpServletRequest request, @PathVariable int itemNumber) {
        String role = (String) request.getAttribute("role");
        System.out.println(role);
        if (role.equalsIgnoreCase("Admin")) {
            iRestaurantService.deleteItemByItemNumber(786, itemNumber);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>("You Are Not Admin", HttpStatus.BAD_REQUEST);
    }

}
