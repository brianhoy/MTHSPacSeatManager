<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<html>
  <head>
    <meta charset="UTF-8">
    <title>Basic Struts 2 Application - Welcome</title>
  </head>
  <body>
    <h1>Welcome To Struts 2!</h1>
   		<a href="index">shows</a> 
		<a href="logout">logout</a> 
		<a href="profile">profile</a>
		<a href="addshow.jsp">add show</a>
		<br>
		<s:property value="msg" />
		<s:property value="errorMsg" />
  </body>
</html>
