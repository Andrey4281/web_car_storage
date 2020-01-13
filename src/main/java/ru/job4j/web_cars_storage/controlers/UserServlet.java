package ru.job4j.web_cars_storage.controlers;

import com.google.common.base.Joiner;
import ru.job4j.web_cars_storage.logic.Logic;
import ru.job4j.web_cars_storage.logic.LogicImpl;
import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public final class UserServlet extends HttpServlet {
    private final Logic logic = LogicImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = "/WEB-INF/Views/user.jsp";
        if ("update".equals(req.getParameter("action"))) {
            req.setAttribute("advert", logic.findAdvertById(Integer.parseInt(req.getParameter("id"))));
            address = "/WEB-INF/Views/edit.jsp";
        } else {
            req.setAttribute("adverts", logic.getAdvertsForCurrentUser((User) req.getSession().getAttribute("user")));
        }
        req.getRequestDispatcher(address).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("update".equals(req.getParameter("action"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            String description = req.getParameter("description");
            boolean status = false;
            if (req.getParameter("status").equals("true")) {
                status = true;
            }
            Advert advert = logic.findAdvertById(id);
            advert.setDescription(description);
            advert.setStatus(status);
            logic.updateAdvert(advert);
        } else if ("delete".equals(req.getParameter("action"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            Advert advert = logic.findAdvertById(id);
            if (advert.getPhoto() != null) {
                File imagine = new File(Joiner.on(File.separator).join("imagesForCarStorage", advert.getPhoto()));
                imagine.delete();
            }
            logic.deleteAdvert(id);
        }
        resp.sendRedirect(String.format("%s/user", req.getContextPath()));
    }
}
