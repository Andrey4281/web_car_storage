package ru.job4j.web_cars_storage.controlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.web_cars_storage.logic.Logic;
import ru.job4j.web_cars_storage.logic.LogicImpl;
import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.service.ContentForCriteria;
import ru.job4j.web_cars_storage.service.CriteriaForFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class GetDataServlet extends HttpServlet {
    private final Logic logic = LogicImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        CriteriaForFilter criteria = new CriteriaForFilter(false, false, false);
        ContentForCriteria content = null;
        if (req.getParameter("brand") != null && req.getParameter("lastDay") != null
        && req.getParameter("withPhoto") != null) {
            boolean isBrand = req.getParameter("brand").equals("All") ? false : true;
            criteria = new CriteriaForFilter(Boolean.valueOf(req.getParameter("lastDay")),
                    Boolean.valueOf(req.getParameter("withPhoto")), isBrand);
            content = new ContentForCriteria(req.getParameter("brand"));
        }
        List<Advert> res = logic.findAdvertsByCriteria(criteria, content);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mapper.setDateFormat(df);
        mapper.writeValue(resp.getOutputStream(), res);
    }
}
