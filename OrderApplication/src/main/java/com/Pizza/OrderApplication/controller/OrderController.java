package com.Pizza.OrderApplication.controller;

import com.Pizza.OrderApplication.domain.Order;
import com.Pizza.OrderApplication.exception.OrderNotFoundException;
import com.Pizza.OrderApplication.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/Order/")
public class OrderController {
    IOrderService iOrderService;
    @Autowired
    public OrderController(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    //http://localhost:7879/api/Order/addNewOrder
    @PostMapping("/addNewOrder")
    public ResponseEntity addNewOrder(HttpServletRequest request, @RequestBody Order order){
        String userEmail = (String) request.getAttribute("userEmail");
        if (userEmail!=null){
            iOrderService.addNewOrder(order);
        }
        return null;
    }
    //http://localhost:7879/api/Order/GetAllOrders
    @GetMapping("/GetAllOrders")
    public ResponseEntity getAllOrderBasedOn(HttpServletRequest request) throws OrderNotFoundException {
        String userEmail = (String) request.getAttribute("userEmail");
        System.out.println(userEmail);
        if (userEmail!=null){
            return new ResponseEntity(iOrderService.getAllOrders(userEmail), HttpStatus.OK);
        }
        return new ResponseEntity("Data Not Found ",HttpStatus.BAD_REQUEST);
    }
    //http://localhost:7879/api/Order/getTotalAmount
    @GetMapping("/getTotalAmount")
    public ResponseEntity getTotalAmount(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        if (userEmail != null) {
            return new ResponseEntity<>(iOrderService.getTotalAmount(userEmail), HttpStatus.OK);
        }
        return new ResponseEntity<>("Amount Not Found !!!", HttpStatus.BAD_GATEWAY);
    }
    //http://localhost:6878/api/Order/getTotalAmountWithUsersInformation
    @GetMapping("/getTotalAmountWithUsersInformation")
    public ResponseEntity getTotalAmountOFAllUsers(HttpServletRequest request){
        String role = (String) request.getAttribute("role");
        System.out.println(role);
        if (role.equalsIgnoreCase("Admin")){
            return new ResponseEntity<>(iOrderService.getAllUsersAmount(),HttpStatus.OK);
        }
        return new ResponseEntity<>("You Are Not Admin",HttpStatus.BAD_GATEWAY);
    }
    //http://localhost:6878/api/Order/getTotalAmountOfAllUsers
    @GetMapping("/getTotalAmountOfAllUsers")
    public ResponseEntity getTotalAmountOfAllAmount(HttpServletRequest request){
        String role = (String) request.getAttribute("role");
        System.out.println(role);
        if (role.equalsIgnoreCase("Admin")){
            return new ResponseEntity<>(iOrderService.getTotalAllUsersAmount(),HttpStatus.OK);
        }
        return new ResponseEntity<>("You Are Not Admin ",HttpStatus.BAD_GATEWAY);

    }
}
