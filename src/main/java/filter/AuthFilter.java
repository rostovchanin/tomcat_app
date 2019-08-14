package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns="/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpSession session = req.getSession(false);

        if((session == null || session.getAttribute("loggedUser") == null)){
            if(!req.getRequestURI().equals("/login")) {
                resp.sendRedirect("/login?needAuth=1");
                return;
            }
        }else if(req.getRequestURI().equals("/login")){
            if(((User)session.getAttribute("loggedUser")).getRole().equals("admin")){
                resp.sendRedirect("/admin");
            }else{
                resp.sendRedirect("/user");
            }
            return;
        }

        chain.doFilter(request, response);

    }
}
