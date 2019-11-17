package servlets;

import javaclasses.GameState;
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

        UserInfo info=(UserInfo) req.getSession().getAttribute("userinfo");
        GameState gs= (GameState) req.getSession().getAttribute("gamestate");
        Message newmessage=new Message(req.getParameter("mess"),info.getLogin(), new Date(System.currentTimeMillis()));
        if(newmessage.mess != null ){
            if(!newmessage.mess.equals(""))
                gs.addMessage(newmessage);
            RequestDispatcher view = req.getRequestDispatcher("main.jsp");
            view.forward(req, resp);
        }
        else {

            String result = "0";
            if (info.reload) {
                result = "1";
                info.reload=false;
            }

            resp.getWriter().write(result);

        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req,resp);
    }


}