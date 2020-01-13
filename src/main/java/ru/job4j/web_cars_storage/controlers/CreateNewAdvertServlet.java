package ru.job4j.web_cars_storage.controlers;

import com.google.common.base.Joiner;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.web_cars_storage.logic.Logic;
import ru.job4j.web_cars_storage.logic.LogicImpl;
import ru.job4j.web_cars_storage.models.Advert;
import ru.job4j.web_cars_storage.models.Car;
import ru.job4j.web_cars_storage.models.User;
import ru.job4j.web_cars_storage.service.AddCarInterface;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public final class CreateNewAdvertServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(CreateNewAdvertServlet.class);
    private final DispatchPattern pattern = new DispatchPattern();
    private final Logic logic = LogicImpl.getInstance();

    private static class DispatchPattern {
        private final Map<String, AddCarInterface> dispatch = new HashMap<>();

        public void init() {
            dispatch.put("category", (car, param)-> {
                car.setCategory(param);
            });
            dispatch.put("brand", (car, param)-> {
                car.setBrand(param);
            });
            dispatch.put("engine", (car, param)-> {
                car.setEngine(param);
            });
            dispatch.put("transmission", (car, param)-> {
                car.setTransmission(param);
            });
            dispatch.put("carcass", (car, param)-> {
                car.setCarcass(param);
            });
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.pattern.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Views/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(req);
            File path = new File("imagesForCarStorage");
            if (!path.exists()) {
                path.mkdirs();
            }
            FileItem fileItemWithPhoto = null;
            Advert advert = new Advert();
            advert.setCreated(new Timestamp(System.currentTimeMillis()));
            advert.setStatus(false);
            Car car = new Car();
            User user = (User) req.getSession().getAttribute("user");
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(Joiner.on(File.separator).join("imagesForCarStorage", "id_user_" + user.getId() + "_" + item.getName()));
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                    advert.setPhoto("id_user_" + user.getId() + "_" + item.getName());
                } else if (item.getFieldName().equals("description")){
                    advert.setDescription(item.getString());
                } else {
                    pattern.dispatch.get(item.getFieldName()).addField(car, item.getString());
                }
            }
            advert.setUser(user);
            Car carFromDataBase = logic.findCarByParameters(car.getCategory(),
                    car.getBrand(), car.getEngine(), car.getTransmission(), car.getCarcass());
            if (carFromDataBase != null) {
                advert.setCar(carFromDataBase);
                logic.addAdvert(advert);
            } else {
                car.setAdverts(new HashSet<>(Arrays.asList(advert)));
                advert.setCar(car);
                logic.addCar(car);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        resp.sendRedirect(String.format("%s/user", req.getContextPath()));
    }
}
