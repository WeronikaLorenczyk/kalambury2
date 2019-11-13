<%@ page import ="javaclasses.GameState" %>
<%@ page import ="java.util.*" %>

<html>

<script>

function fillform(){

var select=document.getElementById('selectgame');

<% List<GameState> mylist =GameState.gamestatelist;
    for(GameState t : mylist){%>
        alert(<%= t.getGamenamewith()%>);
        var opt = document.createElement('option');
        opt.value = <%= t.getGamenamewith()%>;
        opt.innerHTML = opt.value;
        select.appendChild(opt);
    <%}%>

}

</script>

<body  onload="fillform()">
    <h2>
        Hello World!
    </h2>

    Create a new game <br>
    <form action = "selectgame" method = "POST">
        Game name: <input type = "text" name = "gamename"><br>
        <input type = "submit" value = "Create" />
    </form>

    Select game <br>
    <form action = "selectgame" method = "POST"  >
    <select  id="selectgame" name="gamename">
    </select>
    <input type = "submit" value = "Create" />
    </form>


</body>
</html>