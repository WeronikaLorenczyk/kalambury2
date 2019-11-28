
<%@ page import ='servlets.CanvaHandler' %>
<html>
<head>
    <script>
        var ctx,w,h;
        var myURL;

        function init() {

            canvas = document.getElementById('can');
            ctx = canvas.getContext('2d');
            w = canvas.width;
            h = canvas.height;
            setInterval(load,20);
        }


        function load(){
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4) {
                    myURL = xhr.responseText;

                }
            }
            xhr.open('POST', 'canvahandler', false);
            xhr.send(null);

            myURL=decodeURIComponent(myURL)

             if(myURL != null){
                var img = new Image();
                img.onload = function() {
                    ctx.clearRect(0, 0, w, h);
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