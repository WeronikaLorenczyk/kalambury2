
<%@ page import ='servlets.CanvaHandler' %>
<html>
<head>
<script>

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

var ctx,w,h;

function init() {



    canvas = document.getElementById('can');
        ctx = canvas.getContext('2d');
         w = canvas.width;
         h = canvas.height;
        load();


      setInterval(load,1000);

}

function load(){
 var myURL=<%= CanvaHandler.getCanvaURL()  %>;
          ctx.clearRect(0, 0, w, h);

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