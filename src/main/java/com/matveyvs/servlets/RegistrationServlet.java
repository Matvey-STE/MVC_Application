package com.matveyvs.servlets;

import com.matveyvs.dto.UserDto;
import com.matveyvs.entity.UserEntity;
import com.matveyvs.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UserDto userDto = new UserDto();
    UserService validation = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");

        String errorMessage = "";
        String errorAttribute = "error";

        // Perform validation
        if (Boolean.FALSE.equals(validation.isValidName(name))) {
            errorMessage = """
                     Please enter a valid name.
                     The name should start with a capital letter and have 3 to 15 characters.
                     Use only letters (both uppercase and lowercase) without any symbols or numbers.
                    """;
        } else if (Boolean.FALSE.equals(validation.isValidAge(age))) {
            errorMessage = "Please enter a valid age of 18 or older.";
        } else if (Boolean.FALSE.equals(validation.isValidEmail(email))) {
            errorMessage = "Please enter a valid email.";
        } else if (Boolean.FALSE.equals(validation.isValidLogin(login))) {
            errorMessage = """
                    Login must start with a letter (uppercase or lowercase).
                    Login must be 6 to 30 characters long.
                    Use letters, digits, or underscores only in your login.
                    """;
        } else if (Boolean.FALSE.equals(validation.isValidPassword(pwd))) {
            errorMessage = """
                    Password must start with a valid character.
                    Password must contain at least one lowercase letter.
                    Password cannot contain white spaces.
                    Password must be between 5 and 15 characters in length.
                    """;
        } else if (userDto.isLoginExists(login)) {
            errorMessage = "User login already exists.";
        } else if (userDto.isEmailExists(email)) {
            errorMessage = "User email already exists.";
        }

        if (!errorMessage.isEmpty()) {
            req.setAttribute(errorAttribute, errorMessage);
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else {
            req.removeAttribute(errorAttribute);
            UserEntity user = new UserEntity(name, age, email, login, pwd);
            userDto.addUser(user);
            req.setAttribute("user", user);
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        }
    }
}
