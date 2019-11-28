<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.Message" %>
<%@ page import ="javaclasses.UserInfo" %>
<%@ page import ="javaclasses.DatabaseHandler" %>
<%@ page import ="javaclasses.GameState" %>

<html>
   <head>
   </head>

   <body >

        <%
        UserInfo s=(UserInfo) session.getAttribute("userinfo");
        GameState gs=(GameState) session.getAttribute("gamestate");
        for(Object o : gs.players){
            UserInfo ui = DatabaseHandler.getUser( (String) o);
            out.println("<p>"+ui.getLogin()+" score: "+ui.scorenow+" best score: "+ ui.bestscore+"<p>");
        }%>


   </body>
</html>