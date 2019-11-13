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
            for(Object o : messages){
                Message m = (Message) o;
                if(m.nick.equals(s.getLogin())){
                    out.println("<p style='border:2px solid tomato;' align='right'>"+m.nick+": "+m.mess+"</p>");
                }
                else{
                    out.println("<p style='border:2px solid Violet;'>"+m.nick+": "+m.mess+"</p>");
                }
            }
            %>

   </body>
</html>