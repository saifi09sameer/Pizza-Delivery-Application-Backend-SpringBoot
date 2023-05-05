package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;
import com.auth.UserAuthentication.exception.UserAlreadyException;
import com.auth.UserAuthentication.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {

    User addNewUser(User user) throws UserAlreadyException;
    User logINUser(User user)throws UserNotFoundException;

    List<User> getAllUsers();
    boolean deleteByUserEmail(String userEmail);
    
}
