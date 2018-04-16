package util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class studentAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;

        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        if (requestURI.equals("/") || requestURI.equals("/student/login.jsp") || requestURI.equals("/student/register.jsp")) {
            filterChain.doFilter(req, res);
        } else {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("student") == null) {
                res.sendRedirect(req.getContextPath() + "/student/login.jsp");
                return;
            }
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
