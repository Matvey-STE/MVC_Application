package com.matveyvs.servlets;

import com.matveyvs.dto.UserDto;
import com.matveyvs.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
    UserDto userDto = new UserDto();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");

        // Perform validation
        String errorMessage;
        if (!userDto.isLoginExists(login) ||
            !password.equals(userDto.getPasswordByLogin(login).get())) {
            errorMessage = "Wrong login or password.";
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.removeAttribute("error");
            if (userDto.getUserByLogin(login).isPresent()) {
                User user = userDto.getUserByLogin(login).get();
                req.setAttribute("user", user);
            }
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        }
    }
}
