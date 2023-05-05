package com.Pizza.OrderApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "No Orders Founds Exception ")
public class OrderNotFoundException extends Exception{
}
