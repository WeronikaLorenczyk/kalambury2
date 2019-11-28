<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.GameState" %>

<html>
   <head>
   </head>

   <body >
        <% out.clear();
        GameState gs=(GameState) session.getAttribute("gamestate");
        out.println("time left: "+gs.time.get());
        %>
   </body>
</html>