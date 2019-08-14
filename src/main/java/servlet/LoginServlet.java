package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("logout") != null){
            HttpSession session = req.getSession(false);
            if(session != null){
                session.setAttribute("loggedUser", null);
            }
        }
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("login")  == null || req.getParameter("password") == null){
            req.setAttribute("message", "Некорректные данные! Попробуйте еще раз!");
            req.getRequestDispatcher("/views/index.jsp").forward(req,resp);
            return;
        }

        User user = UserServiceImpl.getInstance().getUserByLoginAndPassword(req.getParameter("login"),
                req.getParameter("password"));

        if(user == null){
            req.setAttribute("message", "Ошибка авторизации! Неправильные пароль или логин!");
            req.getRequestDispatcher("/views/index.jsp").forward(req,resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("loggedUser", user);

        resp.sendRedirect("/login");

    }
}
