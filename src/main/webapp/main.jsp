<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.Message" %>
<%@ page import ="javaclasses.UserInfo" %>
<%@ page import ="javaclasses.DatabaseHandler" %>
<%@ page import ="javaclasses.GameState" %>



<html>

   <head>
      <title>Messenger</title>


      <script>

      function init(){
      alert('a');
      reload();
      console.log("a");
      setInterval(reload,1000);
      }

      function reload(){
      var iframe = document.getElementById('iframe');
      iframe.src = "./messagespage.jsp";
      console.log("a");
      }

      </script>
   </head>

   <body onload='init()'>

            <%
            UserInfo ui=(UserInfo) session.getAttribute("userinfo");
            GameState gs=(GameState) session.getAttribute("gamestate");
            System.out.println(gs.getGamename());
            out.println("<br>Messages: <br><br>");
            %>

            <iframe id="iframe" src="./messagespage.jsp" height="200" width="300"></iframe>

            <form action = "sendmessage" method = "POST">
             Message: <input type = "text" name = "mess">
             <input type = "submit" value = "Submit" />
             </form>
             <br><br><br>


             <% if(ui.getLogin().equals(gs.getDrawing())){ %>
             <iframe style="border:none;" src="./canva.jsp" height="700" width="500"></iframe>


             <% }
             else {%>
             <iframe style="border:none;" src="./picture.jsp" height="500" width="500" ></iframe>

             <% } %>

   </body>
</html>