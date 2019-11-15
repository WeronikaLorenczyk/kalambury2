<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.Message" %>
<%@ page import ="javaclasses.UserInfo" %>
<%@ page import ="javaclasses.DatabaseHandler" %>
<%@ page import ="javaclasses.GameState" %>

<html>
   <head>
      <title>Messenger</title>
   </head>

   <body>

            <%
            List messages=DatabaseHandler.getMessagesList();
            UserInfo ui=(UserInfo) session.getAttribute("userinfo");
            GameState gs=(GameState) session.getAttribute("gamestate");
            System.out.println(gs.getGamename());
            out.println("<br>Messages: <br><br>");
            %>

            <iframe src="./messagespage.jsp" height="200" width="300"></iframe>

            <form action = "sendmessage" method = "POST">
             Message: <input type = "text" name = "mess">
             <input type = "submit" value = "Submit" />
             </form>
             <br><br><br>


             <% if(ui.getLogin().equals(gs.getDrawing())){ %>
               <jsp:include page="canva.jsp" />

             <% }
             else {%>
             <jsp:include page="picture.jsp" />
             <% } %>

   </body>
</html>