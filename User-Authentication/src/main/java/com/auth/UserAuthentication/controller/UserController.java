package com.auth.UserAuthentication.controller;

import com.auth.UserAuthentication.domain.User;
import com.auth.UserAuthentication.exception.UserAlreadyException;
import com.auth.UserAuthentication.exception.UserNotFoundException;
import com.auth.UserAuthentication.service.ITokenGenerate;
import com.auth.UserAuthentication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//http://localhost:7867/api/Authentication/ [BASE URL]

@RestController
@RequestMapping("/api/Authentication/")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    IUserService iUserService;
    ITokenGenerate iTokenGenerate;
    @Autowired
    public UserController(IUserService iUserService, ITokenGenerate iTokenGenerate) {
        this.iUserService = iUserService;
        this.iTokenGenerate = iTokenGenerate;
    }
    //http://localhost:7867/api/Authentication/register [POST] + [BODY]
    @PostMapping("/register")
    public ResponseEntity addNewUser(@RequestBody User user) throws UserAlreadyException {
        return new ResponseEntity<>(iUserService.addNewUser(user), HttpStatus.CREATED);
    }
    //http://localhost:7867/api/Authentication/login [POST] [BODY]
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
       User retriveUser =  iUserService.logINUser(user);
        Map<String,String> token = iTokenGenerate.generateUserToken(retriveUser);
       if (retriveUser!=null){
           return new ResponseEntity<>(token,HttpStatus.OK);
       }
        return new ResponseEntity<>("User Not Fond ",HttpStatus.NOT_FOUND);
    }
    //http://localhost:7867/api/Authentication/getAllUsers
    @GetMapping("/getAllUsers")
    public  ResponseEntity getAllUsers(HttpServletRequest request){
        String role = (String) request.getAttribute("role");
        if (role.equalsIgnoreCase("Admin")){
            return new ResponseEntity<>(iUserService.getAllUsers(),HttpStatus.OK);
        }
        return new ResponseEntity("You Are User Not Admin",HttpStatus.OK);
    }
    //http://localhost:7867/api/Authentication/deleteByUserEmail/{userEmail}
    @DeleteMapping("/deleteByUserEmail/{userEmail}")
    public ResponseEntity deleteByUserEmail(HttpServletRequest request,@PathVariable String userEmail){
        String role = (String) request.getAttribute("role");
        if (role.equalsIgnoreCase("Admin")){
            return new ResponseEntity<>(iUserService.deleteByUserEmail(userEmail),HttpStatus.OK);
        }
        return new ResponseEntity("Not Deleted",HttpStatus.BAD_REQUEST);
    }

}
