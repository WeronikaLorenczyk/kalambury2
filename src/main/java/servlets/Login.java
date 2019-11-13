package servlets;


import javaclasses.DatabaseHandler;
import javaclasses.UserInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        UserInfo ui = new UserInfo(req.getParameter("login"),req.getParameter("password"));
        boolean uexist = DatabaseHandler.userExist(ui.getLogin(),ui.getPassword());
        if(uexist){
            UserInfo realui = DatabaseHandler.getUser(ui.getLogin());
            req.getSession().setAttribute("userinfo",realui);
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



