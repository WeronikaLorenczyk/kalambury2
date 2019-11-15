package servlets;


import javaclasses.GameState;
import javaclasses.UserInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SelectGame extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String newgamename= req.getParameter("gamename");
        UserInfo ui= (UserInfo) req.getSession().getAttribute("userinfo");


        for(GameState gs: GameState.gamestatelist){
            if(gs.getGamename().equals(newgamename)){
                gs.addplayer(ui.getLogin());
                req.getSession().setAttribute("gamestate",gs);
                RequestDispatcher view = req.getRequestDispatcher("main.jsp");
                view.forward(req, resp);
                return;
            }
        }


        GameState newgs= new GameState(newgamename);
        newgs.addplayer(ui.getLogin());
        GameState.gamestatelist.add(newgs);
        req.getSession().setAttribute("gamestate",newgs);
        RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req,resp);
    }

}