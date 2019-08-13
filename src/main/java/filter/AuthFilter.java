package filter;

import model.User;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("loggedUserId") == null){
            if(req.getRequestURI().equals("/")){
                chain.doFilter(request, response);
                return;
            }else{
                ((HttpServletResponse)response).sendRedirect("/?needAuth=1");
                return;
            }
        }

        User user = UserServiceImpl.getInstance().getUserById((Integer)session.getAttribute("loggedUserId"));
        session.setAttribute("loggedUser", user);

        if(!user.getRole().equals("admin") && req.getRequestURI().contains("/admin")){
            ((HttpServletResponse)response).sendRedirect("/?accessDeny=1");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
