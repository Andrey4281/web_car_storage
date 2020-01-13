package ru.job4j.web_cars_storage.controlers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (checkRequest(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (session.getAttribute("user") == null) {
            ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/signin", request.getContextPath()));
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean checkRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("/signin")
                || request.getRequestURI().contains("/static")
                || request.getRequestURI().contains("/signup")
                || request.getRequestURI().contains("/data")
                || request.getRequestURI().contains("/download");
    }
}
