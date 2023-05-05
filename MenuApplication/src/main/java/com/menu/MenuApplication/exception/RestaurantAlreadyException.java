package com.menu.MenuApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Restaurant Already Exception")
public class RestaurantAlreadyException extends Exception{
}
