<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.Message" %>
<%@ page import ="javaclasses.UserInfo" %>
<%@ page import ="javaclasses.DatabaseHandler" %>

<html>
   <head>
      <title>Messenger</title>
   </head>

   <body>



            <%
            List messages=DatabaseHandler.getMessagesList();
            UserInfo s=(UserInfo) session.getAttribute("userinfo");
            out.println("<br>Messages: <br><br>");
            %>

            <iframe src="./messagespage.jsp" height="200" width="300"></iframe>

            <form action = "sendmessage" method = "POST">
             Message: <input type = "text" name = "mess">
             <input type = "submit" value = "Submit" />
             </form>
             <br><br><br>

             <jsp:include page="canva.jsp" />

   </body>
</html>