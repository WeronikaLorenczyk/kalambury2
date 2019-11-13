package servlets;

import javax.servlet.RequestDispatcher;
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
        System.out.println(canvaURL);

        System.out.println(canvaURL.equals(oldURL));
        /*String differences="";
        for(int i=0; i<oldURL.length() && i<canvaURL.length();i++){
            if(oldURL.charAt(i) != canvaURL.charAt(i) )
                differences= differences+i+' '+oldURL.charAt(i)+' '+canvaURL.charAt(i)+' ';
        }
        System.out.println(differences);*/


        RequestDispatcher view = req.getRequestDispatcher("main.jsp");
        view.forward(req, resp);
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