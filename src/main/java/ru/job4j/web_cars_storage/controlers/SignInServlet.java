package ru.job4j.web_cars_storage.controlers;

import ru.job4j.web_cars_storage.logic.Logic;
import ru.job4j.web_cars_storage.logic.LogicImpl;
import ru.job4j.web_cars_storage.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class SignInServlet extends HttpServlet {
    private final Logic logic = LogicImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Views/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = logic.findUserByLogin(login);
        if (user == null) {
            req.setAttribute("error", String.format("User %s does not exist. You can sign up", login));
            doGet(req, resp);
        } else if (!password.equals(user.getPassword())){
            req.setAttribute("error", String.format("Invalid password for user %s", login));
            doGet(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(String.format("%s/user", req.getContextPath()));
        }
    }
}
