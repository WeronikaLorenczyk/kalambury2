
<%@ page import ='servlets.CanvaHandler' %>
<html>
<head>
<script>


function init() {

    canvas = document.getElementById('can');
        ctx = canvas.getContext('2d');
       
        var myURL=<%= CanvaHandler.getCanvaURL()  %>;

         if(myURL != null){

            var img = new Image();
            img.onload = function() {
               ctx.drawImage(img, 0, 0);
            };
            img.src = myURL;
             }
}


</script>

</head>
<body onload='init()'>

 <canvas id='can' width='400' height='400' style='border:2px solid;'></canvas>
</body>
</html>