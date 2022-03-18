package com.stellaweb.webdemo;


import com.stellaweb.webdemo.controllers.AuthenticationController;
import com.stellaweb.webdemo.models.User;
import com.stellaweb.webdemo.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/", "/register", "/logout", "/post/create","/post", "/create", "/static/Logan-BG-draft-01.png", "/styles.css", "/css/edit_table.css", "/javascript/edit_table.js");

//    private static final List<String> whitelist = Arrays.asList("/login",  "/static/Logan-BG-draft-01.png", "/styles.css", "/css/edit_table.css", "/javascript/edit_table.js");


    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (isWhitelisted(request.getRequestURI())) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        if (user != null) {
            return true;
        }
        response.sendRedirect("/login");
        return false;

    }

}