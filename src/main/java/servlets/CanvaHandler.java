package servlets;

import javaclasses.GameState;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class CanvaHandler extends HttpServlet {

   public  String canvaURL=null;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newURL=req.getParameter("canvasURL");
        GameState gs = (GameState) req.getSession().getAttribute("gamestate");
        if(newURL != null)
            gs.picture.canvaURL=newURL;
        else if(gs!= null && gs.picture.canvaURL != null){

            String result;
             result = URLEncoder.encode(gs.picture.canvaURL, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");

            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(result);
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req,resp);
    }

    public  String getCanvaURL(){
        if(canvaURL == null){
            return null;
        }
        String a="'"+canvaURL+"'";
        return a;
    }

}