package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;

import java.util.Map;

public interface ITokenGenerate {
    Map<String,String> generateUserToken(User user);
}
