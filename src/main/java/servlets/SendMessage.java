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
        String start=req.getParameter("start");
        if(start != null){
            gs.roundstarted=true;
            gs.picture.canvaURL=null;
            RequestDispatcher view = req.getRequestDispatcher("main.jsp");
            view.forward(req, resp);
            return;
        }

        Message newmessage=new Message(req.getParameter("mess"),info.getLogin(), new Date(System.currentTimeMillis()));
        if(newmessage.mess != null){
            if(!newmessage.mess.equals(""))
                gs.addMessage(newmessage);
            if(newmessage.mess.equals(gs.word) && !newmessage.nick.equals(gs.getDrawing())){
                gs.nextround();
                gs.addpoint(newmessage.nick);
            }
            RequestDispatcher view = req.getRequestDispatcher("main.jsp");
            view.forward(req, resp);
            return;

        }
        String reload=req.getParameter("reload");
        if(reload != null) {
            String result = "";
            if (info.reload) {
                result = "all";
                info.reload=false;
            }
            else if(info.newmessage){
                result="messages";
                info.newmessage=false;
            }
            resp.getWriter().write(result);
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req,resp);
    }


}