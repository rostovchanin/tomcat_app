package servlet;

import com.mysql.cj.util.StringUtils;
import exception.LogicException;
import javafx.fxml.LoadException;
import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null && UserServiceImpl.getInstance().isCorrectedId(req.getParameter("id"))){

            int UserId = Integer.parseInt(req.getParameter("id"));

            try{
                UserServiceImpl.getInstance().deleteUserById(UserId);
                resp.sendRedirect("/admin?deleteOk=1");
            }catch(LogicException e){
                resp.sendRedirect("/admin?deleteNotOk=1");
            }
            return;
        }
        resp.sendRedirect("/admin?deleteNotOk=1");

    }
}
