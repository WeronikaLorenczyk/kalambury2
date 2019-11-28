<%@ page import ="javaclasses.GameState" %>
<%@ page import ="java.util.*" %>

<html>
<head>
      <title>kalambury</title>
 </head>

<script>

    function fillform(){
        var select=document.getElementById('selectgame');
        <% List<GameState> mylist =GameState.gamestatelist;
        for(GameState t : mylist){%>
            var opt = document.createElement('option');
            opt.value = <%= t.getGamenamewith()%>;
            opt.innerHTML = opt.value;
            select.appendChild(opt);
        <%}%>
    }

</script>

<body  onload="fillform()">
    <h2>
        Select or create game!
    </h2>

   <h4> Create a new game <br></h4>
    <form action = "selectgame" method = "POST">
        Game name: <input type = "text" name = "gamename"><br>
        <input type = "submit" value = "Create" />
    </form>
    <br><br>

    <h4> Select game <br></h4>
    <form action = "selectgame" method = "POST"  >
        <select  id="selectgame" name="gamename"></select>
        <input type = "submit" value = "Select" />
    </form>


</body>
</html>