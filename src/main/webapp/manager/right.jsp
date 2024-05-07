<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title></title>
    <style>
       body{
      font-family: 微软雅黑;
    }
     #msg{
       text-align:center;
       margin-top:200px;
     }
    </style>
  </head>
  <body>

      <c:if test="${manager == null }">
        <h1 class="msg">对不起，请先登录</h1>
      </c:if>
      <c:if test="${manager != null}">
        <h1 class="msg">欢迎来到后台管理系统！</h1>
      </c:if>

      <h1 id="msg">${msg}</h1>

  </body>
</html>