package ru.job4j.web_cars_storage.controlers;

import ru.job4j.web_cars_storage.logic.Logic;
import ru.job4j.web_cars_storage.logic.LogicImpl;
import ru.job4j.web_cars_storage.models.Role;
import ru.job4j.web_cars_storage.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public final class SignUpServlet extends HttpServlet {
    private final Logic logic = LogicImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String phone = req.getParameter("phone");
        List<User> users = logic.findUsersByLoginOrPhone(login, phone);
        if (!users.isEmpty()) {
            String message = null;
            Optional<User> var = users.stream().filter(user -> user.getPhone().equals(phone)).findFirst();
            if (!var.isEmpty()) {
                message = String.format("User with phone %s already exists in system. Please choose another phone", phone);
            } else {
                message = String.format("User %s already exists in system. Please choose another login", login);
            }
            req.setAttribute("error", message);
            doGet(req, resp);
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPhone(phone);
            user.setPassword(req.getParameter("password"));
            user.setRole(new Role(2, "user"));
            logic.addUser(user);
            resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
        }
    }
}
