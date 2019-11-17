<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.Message" %>
<%@ page import ="javaclasses.UserInfo" %>
<%@ page import ="javaclasses.DatabaseHandler" %>
<%@ page import ="javaclasses.GameState" %>



<html>

   <head>
      <title>Messenger</title>


      <script>
      var iframe

      function init(){
      iframe = document.getElementById('iframe');

      setInterval(load,1000);
      }

      function load1(){
      var form=document.getElementById("form");
      document.getElementById("mess").value="";
      form.submit();

      }

      function load(){
      iframe.src="./messagespage.jsp";
      var reload;
      var xhr = new XMLHttpRequest();
          xhr.onreadystatechange = function() {
              if (xhr.readyState == 4) {
                  reload = xhr.responseText;
                  if(reload == "1"){
                  load1();
                  }
              }
          }
          xhr.open('POST', 'sendmessage', true);
          var params = 'mess=';
          xhr.send(params);

      }

      </script>
   </head>

   <body onload='init()'>

            <%
            UserInfo ui=(UserInfo) session.getAttribute("userinfo");
            GameState gs=(GameState) session.getAttribute("gamestate");
            out.println("<br>Messages: <br><br>");
            %>

            <iframe id="iframe" src="./messagespage.jsp" height="200" width="300"></iframe>

            <form action = "sendmessage" method = "POST" id="form">
             Message: <input type = "text" name = "mess" id="mess">
             <input type = "submit" value = "Submit" />
             </form>
             <br><br><br>


             <% if(ui.getLogin().equals(gs.getDrawing())){
             out.println("Draw: "+gs.word);

             %>
             <iframe style="border:none;" src="./canva.jsp" height="700" width="500"></iframe>
             <% }
             else {%>
             <iframe style="border:none;" src="./picture.jsp" height="500" width="500" ></iframe>

             <% } %>

   </body>
</html>