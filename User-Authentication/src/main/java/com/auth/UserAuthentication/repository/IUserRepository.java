package com.auth.UserAuthentication.repository;

import com.auth.UserAuthentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,String> {
    User findByUserEmailOrUserPassword(String userEmail, String userPassword);

}
