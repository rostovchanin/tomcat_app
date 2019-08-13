package servlet;

import com.mysql.cj.util.StringUtils;
import exception.LogicException;
import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/editUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if(req.getParameter("id") != null && UserServiceImpl.getInstance().isCorrectedId(req.getParameter("id"))){
            int UserId = Integer.parseInt(req.getParameter("id"));
            User editUser = UserServiceImpl.getInstance().getUserById(UserId);
            if(editUser != null){
                req.setAttribute("editUser", editUser);
            }
        }
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/views/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if(!isCorrectPostParameters(req)){
            req.setAttribute("message", "Некорректные данные! Попробуйте еще раз!");
            req.getRequestDispatcher("/views/editUser.jsp").forward(req, resp);
            return;
        }
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setRole(req.getParameter("role"));

        try{
            if(req.getParameter("id") != null){
                user.setId(Integer.parseInt(req.getParameter("id")));
                UserServiceImpl.getInstance().updateUser(user);
            }else{
                UserServiceImpl.getInstance().createUser(user);
            }
            resp.sendRedirect("/admin?updateOk=1");
        }catch (LogicException e){
            resp.sendRedirect("/admin?updateNotOk=1");
        }
    }

    private boolean isCorrectPostParameters(HttpServletRequest req){
        if(req.getParameter("name") == null ||
                req.getParameter("password") == null ||
                req.getParameter("login") == null ||
                req.getParameter("role") == null){

            return false;
        }
        if(req.getParameter("id") != null && !UserServiceImpl.getInstance().isCorrectedId(req.getParameter("id"))){
            return false;
        }
        return true;
    }
}
