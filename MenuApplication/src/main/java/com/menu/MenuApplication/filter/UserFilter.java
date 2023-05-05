package com.menu.MenuApplication.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("Authorization");
        System.out.println(authHeader);
        if (authHeader ==null || !authHeader.startsWith("Bearer")){
            throw new ServletException("Token is missing");
        }else{
            String token=authHeader.substring(7);
            System.out.println("Token is = "+token);
            Claims claims= Jwts.parser().setSigningKey("secretKeyData").parseClaimsJws(token).getBody();
            System.out.println("Retrieved Claims "+ claims);
            httpServletRequest.setAttribute("userEmail", claims.get("userEmail"));
            httpServletRequest.setAttribute("userName", claims.get("userName"));
            httpServletRequest.setAttribute("userPhoneNumber",claims.get("userPhoneNumber"));
            httpServletRequest.setAttribute("userAddress",claims.get("userAddress"));
            httpServletRequest.setAttribute("role",claims.get("role"));
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}