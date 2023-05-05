package com.menu.MenuApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Restaurant Not FoundException")
public class RestaurantNotFoundException extends Exception {
}
