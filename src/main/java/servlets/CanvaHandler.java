package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CanvaHandler extends HttpServlet {

    static String canvaURL=null;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String oldURL=canvaURL;

        canvaURL=req.getParameter("canvasURL");

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req,resp);
    }

    public static String getCanvaURL(){
        if(canvaURL == null){
            return null;
        }
        String a="'"+canvaURL+"'";
        System.out.println("!!!!!!!!"+a);
        return a;
    }

    public static void setCanvaURL(String canvaURL) {
        CanvaHandler.canvaURL = canvaURL;
    }
}