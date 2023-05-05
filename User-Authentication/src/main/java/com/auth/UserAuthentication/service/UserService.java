package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;
import com.auth.UserAuthentication.exception.UserAlreadyException;
import com.auth.UserAuthentication.exception.UserNotFoundException;
import com.auth.UserAuthentication.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{
    IUserRepository iUserRepository;
    @Autowired
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public User addNewUser(User user) throws UserAlreadyException {
        user.setUserRole("User");
        if (iUserRepository.findById(user.getUserEmail()).isEmpty()){
           return iUserRepository.save(user);
        }
        throw new UserAlreadyException();
    }

    @Override
    public User logINUser(User user) throws UserNotFoundException {
        if (iUserRepository.findById(user.getUserEmail()).isEmpty()){
            throw new UserNotFoundException();
        }
        return iUserRepository.findByUserEmailOrUserPassword(user.getUserEmail(), user.getUserPassword());
    }

    @Override
    public List<User> getAllUsers() {
        return iUserRepository.findAll()
                .stream()
                .filter(user -> !user.getUserRole().equalsIgnoreCase("Admin"))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteByUserEmail(String userEmail) {
        iUserRepository.deleteById(userEmail);
        return true;
    }

}
