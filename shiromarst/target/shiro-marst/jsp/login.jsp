<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="../static/js/jquery-3.3.1.min.js"></script>
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background: url(../static/images/a.jpg)repeat;
        }
        #login-box {
            /*border:1px solid #F00;*/
            padding: 35px;
            border-radius:15px;
            background: #56666B;
            color: #fff;
        }

    </style>
</head>
<body>
<div class="container" id="top">
    <div class="row" style="margin-top: 280px; ">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="login-box">
            <form class="form-horizontal" role="form"  id="from1" method="post">
                <div class="form-group">
                    <label for="firstname" class="col-sm-3 control-label">用户id</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="userID" placeholder="请输入名字" name="username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-sm-3 control-label">密码</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
                    </div>
                </div>
                <div class="form-group pull-right" style="margin-right: 15px;">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" onclick="login()" class="btn btn-default btn-info">登录</button>
                    </div>

                </div>
                <div class="form-group pull-left" style="margin-right: 15px;">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" onclick="window.location.href='/jsp/register.jsp'" class="btn btn-default btn-info">去注册</button>
                    </div>

                </div>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<script>
    document.onkeydown = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            login();
        }
    }
    function login() {
        $.ajax({
            url:"/login/login",
            data:$("#from1").serialize(),
            type:"post",
            dataType:"json",
            success(data){
                if(data.code==200){
                    console.info(data.extend.url);
                    window.location.href=data.extend.url;
                }else{
                    alert(data.code+"  "+data.extend.code);
                }
            }
        })
    }
</script>
</body>
</html>