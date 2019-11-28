<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.Message" %>
<%@ page import ="javaclasses.UserInfo" %>
<%@ page import ="javaclasses.DatabaseHandler" %>
<%@ page import ="javaclasses.GameState" %>

<html>
   <head>
   </head>

   <body >

        <% out.clear();

        UserInfo s=(UserInfo) session.getAttribute("userinfo");
        GameState gs=(GameState) session.getAttribute("gamestate");
        List messages= new ArrayList(gs.getMessagesList());
        for(Object o : messages){
            Message m = (Message) o;
            if(m.mess.equals(gs.word) && ! m.nick.equals(gs.getDrawing())){
                out.println("<p style='border:5px solid green;' align='right'>"+m.nick+": "+m.mess+"</p>");
            }
            else if(m.mess.equals(gs.word+" ")){
                out.println("<p style='border:5px solid green;' align='right'>"+m.nick+": "+m.mess+"</p>");
            }
            else if(m.nick.equals(s.getLogin())){
                out.println("<p style='border:2px solid tomato;' align='right'>"+m.nick+": "+m.mess+"</p>");
            }
            else{
                out.println("<p style='border:2px solid Violet;'>"+m.nick+": "+m.mess+"</p>");
            }
        }%>


   </body>
</html>