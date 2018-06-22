<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="en">
<%@include file="db/db.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="${path }/static/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <script type="text/javascript"
            src="${path }/static/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript"
            src="${path }/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 显示表单 -->
<div class="row">
    <div class="col-md-12">
        <table class="table table-striped">
            <tr>
                <th>#id</th>
                <th>姓名</th>
                <th>开始会话时间</th>
                <th>最后活动时间</th>
                <th>踢出状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${user }" var="p">
                <tr>
                    <td>#${p.sessionid }</td>
                    <td>${p.username }</td>
                    <td><fmt:formatDate value="${p.starttime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${p.lasttime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>
                        <c:if test="${p.status==true}">否</c:if>
                        <c:if test="${p.status==false}">是</c:if>
                    </td>
                    <td>
                        <button class="btn btn-default btn-success btn-sm">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;详情
                        </button>
                        <button class="btn btn-default btn-danger btn-sm" onclick="outid(${p.id})">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;踢出
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script>
    function outid(id) {
        $.ajax({
            url:"/UserController/outid",
            data:{id:id},
            success(data){
                alert(666);
            }
        })
    }
</script>
</body>
</html>