package servlets;

import javaclasses.DatabaseHandler;
import javaclasses.UserInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Signin extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        UserInfo ui = new UserInfo(req.getParameter("login"),req.getParameter("password"));
        ui.setAge(Integer.valueOf(req.getParameter("age")));
        boolean added = DatabaseHandler.addUser(ui);
        if(added){
            req.getSession().setAttribute("userinfo",ui);
            RequestDispatcher view = req.getRequestDispatcher("main.jsp");
            view.forward(req, resp);
        }
        else{
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            view.forward(req, resp);
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req,resp);
    }

}
