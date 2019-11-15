package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class CanvaHandler extends HttpServlet {

    static String canvaURL=null;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newURL=req.getParameter("canvasURL");
        if(newURL != null)
            canvaURL=newURL;
        else if(canvaURL != null){

            String result;
             result = URLEncoder.encode(canvaURL, "UTF-8")
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