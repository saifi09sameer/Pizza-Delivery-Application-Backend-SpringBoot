package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class TokenGenerate implements ITokenGenerate{

    @Override
    public Map<String, String> generateUserToken(User user) {
        Map<String,String> result= new HashMap<>();
        Map<String,Object> userData = new HashMap<>();
        userData.put("userEmail",user.getUserEmail() );
        userData.put("userName",user.getUserName());
        userData.put("userPhoneNumber",user.getUserPhoneNumber());
        userData.put("userAddress",user.getUserAddress());
        userData.put("role",user.getUserRole());
        String myToken= Jwts.builder().setClaims(userData)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,"secretKeyData")
                .compact();
        result.put("Token",myToken);
        result.put("userName",user.getUserName());
        result.put("userEmail", user.getUserEmail());
        result.put("userAddress", user.getUserAddress());
        result.put("userPhoneNumber",user.getUserPhoneNumber());
        result.put("userRole",user.getUserRole());
        result.put("Message","("+user.getUserName()+")"+" login successful");
        return result;

    }
}
