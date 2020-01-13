package ru.job4j.web_cars_storage.controlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.web_cars_storage.logic.Logic;
import ru.job4j.web_cars_storage.logic.LogicImpl;
import ru.job4j.web_cars_storage.models.Advert;

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
        List<Advert> res = logic.getAllAdverts();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mapper.setDateFormat(df);
        mapper.writeValue(resp.getOutputStream(), res);
    }
}
