package servlets;

import javaclasses.DatabaseHandler;
import javaclasses.Message;
import javaclasses.UserInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class SendMessage extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        resp.getWriter().print("Hello from servlet");
        UserInfo info=(UserInfo) req.getSession().getAttribute("userinfo");
        Message newmessage=new Message(req.getParameter("mess"),info.getLogin(), new Date(System.currentTimeMillis()));
        DatabaseHandler.addMessage(newmessage);

        RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req,resp);
    }


}