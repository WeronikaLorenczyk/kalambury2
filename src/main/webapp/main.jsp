<%@ page import ="java.util.*" %>
<%@ page import ="javaclasses.Message" %>
<%@ page import ="javaclasses.UserInfo" %>
<%@ page import ="javaclasses.DatabaseHandler" %>
<%@ page import ="javaclasses.GameState" %>



<html>

   <head>
      <title>kalambury</title>
            <script>
            var iframe
             var timer;


            function init(){
                  <%UserInfo ui=(UserInfo) session.getAttribute("userinfo");
                   GameState gs=(GameState) session.getAttribute("gamestate");%>
            iframe = document.getElementById('iframe');

                  timer=document.getElementById('timer');
                  setInterval(load,1000);
            }

            function load1(){
            var form=document.getElementById("form");
            document.getElementById("mess").value="";
            form.submit();
            }
            function load(){
            timer.src="./timer.jsp";
            var reload;
            var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4) {
                        reload = xhr.responseText;
                        if(reload == "all"){
                            load1();
                        }
                        if(reload == "messages"){
                            iframe.src="./messagespage.jsp";
                        }
                    }
                }
                xhr.open('POST', 'sendmessage', true);
                xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
                var params = 'reload=a';
                xhr.send(params);

            }

            </script>
   </head>

   <body onload='init()'>
   <table>
   <tr>
   <th>

            <%
            out.println("<p>Messages: <p><br><br>");
            %>

    </th>
    <th>
             <% if(ui.getLogin().equals(gs.getDrawing())){
                                                  out.println("<h3>draw: "+gs.word+"</h3>");} %>
    </th>
    </tr>
    <tr>
    <th>


            <iframe id="iframe" src="./messagespage.jsp" height="300" width="400"></iframe>

            <form action = "sendmessage" method = "POST" id="form">
             Message: <input type = "text" name = "mess" id="mess">
             <input type = "submit" value = "Submit" />
             </form>

             <br><br>
             <iframe style="border:none;" id="timer" src="./timer.jsp" height="50" width="400"></iframe>
             <br>

             <form action = "sendmessage" method = "POST" id="startround">
             <% if(ui.getLogin().equals(gs.getDrawing()) && ! gs.roundstarted){ %>
             <input type="hidden" name="start" id="start" value="s">
                          <input type = "submit" value = "Start new round" />
                          </form>
              <%}%>

</th>
<th>

             <% if(ui.getLogin().equals(gs.getDrawing())){ %>
             <iframe style="border:none;" src="./canva.jsp" height="500" width="500"></iframe>
             <% }
             else {%>
             <iframe style="border:none;" src="./picture.jsp" height="500" width="500" ></iframe>

             <% } %>
     </th>
     </tr>
     </table>

   </body>
</html>