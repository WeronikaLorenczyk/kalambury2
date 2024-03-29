
<%@ page import ='servlets.CanvaHandler' %>
<%@ page import ='javaclasses.GameState' %>
<html>
<head>
<script>
var canvas, ctx, flag = false,
    prevX = 0,
    currX = 0,
    prevY = 0,
    currY = 0,
    dot_flag = false;

var x = 'black',
    y = 2;

function init() {
    <%
        GameState gs=(GameState) session.getAttribute("gamestate");
        System.out.println(gs.getGamename());
     %>
    canvas = document.getElementById('can');
    ctx = canvas.getContext('2d');
    w = canvas.width;
    h = canvas.height;
    var myURL=<%= gs.picture.getCanvaURL()  %>;

     if(myURL != null){

        var img = new Image();
        img.onload = function() {
           ctx.drawImage(img, 0, 0);
        };
        img.src = myURL;
         }

    canvas.addEventListener('mousemove', function (e) {
        findxy('move', e)
    }, false);
    canvas.addEventListener('mousedown', function (e) {
        findxy('down', e)
    }, false);
    canvas.addEventListener('mouseup', function (e) {
        findxy('up', e)
    }, false);
    canvas.addEventListener('mouseout', function (e) {
        findxy('out', e)
    }, false);
}

function color(obj) {
    x=obj.id;
    if (x == 'white') y = 14;
    else y = 2;

}
var c;
function draw() {
    ctx.beginPath();
    ctx.moveTo(prevX, prevY);
    ctx.lineTo(currX, currY);
    ctx.strokeStyle = x;
    ctx.lineWidth = y;
    ctx.stroke();
    ctx.closePath();
    save2();

}

function erase() {
    var m = confirm('Want to clear');
    if (m) {
        ctx.clearRect(0, 0, w, h);
        document.getElementById('canvasimg').style.display = 'none';
        save2();
    }
}

function save() {
    save2();
    document.getElementById('canvasimg').style.border = '2px solid';
    var dataURL = canvas.toDataURL();
    var mydataURL = <%= gs.picture.getCanvaURL()  %>;

    document.getElementById('canvasimg').src = mydataURL;
    document.getElementById('canvasimg').style.display = 'inline';
}

function save2(){

    var dataURL = canvas.toDataURL();
     var http = new XMLHttpRequest();
     http.open('POST', 'canvahandler', false);
     http.setRequestHeader('Content-type','application/x-www-form-urlencoded');
     var params = 'canvasURL=' + encodeURIComponent(dataURL);
     http.send(params);
}


function findxy(res, e) {
    if (res == 'down') {
        prevX = currX;
        prevY = currY;
        currX = e.clientX - canvas.offsetLeft;
        currY = e.clientY - canvas.offsetTop;

        flag = true;
        dot_flag = true;
        if (dot_flag) {
            ctx.beginPath();
            ctx.fillStyle = x;
            ctx.fillRect(currX, currY, 2, 2);
            ctx.closePath();
            dot_flag = false;
        }
    }
    if (res == 'up' || res == 'out') {
        flag = false;
    }
    if (res == 'move') {
        if (flag) {
            prevX = currX;
            prevY = currY;
            currX = e.clientX - canvas.offsetLeft;
            currY = e.clientY - canvas.offsetTop;
            draw();
        }
    }
}

</script>


</head>
<body onload='init()'>
<table>
<tr>
<th>
    <canvas id='can' width='400' height='400' style='border:2px solid;'></canvas>
</th>
<th>
    <div >Choose Color</div>
    <div style='width:15px;height:15px;background:green;' id='green' onclick='color(this)'></div>
    <div style='width:15px;height:15px;background:blue;' id='blue' onclick='color(this)'></div>
    <div style='width:15px;height:15px;background:red;' id='red' onclick='color(this)'></div>
    <div style='width:15px;height:15px;background:yellow;' id='yellow' onclick='color(this)'></div>
    <div style='width:15px;height:15px;background:orange;' id='orange' onclick='color(this)'></div>
    <div style='width:15px;height:15px;background:black;' id='black' onclick='color(this)'></div>
    <div >Eraser</div>
    <div style='width:20px;height:20px;background:white;border:2px solid;' id='white' onclick='color(this)'></div>

    <input type='button' value='clear' id='clr' size='23' onclick='erase()' >
</th>
<tr>
</table
</body>
</html>