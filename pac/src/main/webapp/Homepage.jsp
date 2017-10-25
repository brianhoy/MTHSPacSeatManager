<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MTHS Performing Arts Center</title>
  </head>
  <body>
  	<h1>MTHS Performing Arts Center</h1>
 	<p><a href="<s:url action='login'/>">Admin Login</a></p>
  	<h2>Shows</h2>

  	
	<p>To be added...</p>
	
	<s:iterator status="stat" value="(5).{ #this }" >
	   <s:property value="#stat.count" /> <!-- Note that "count" is 1-based, "index" is 0-based. -->
	</s:iterator>
		
	<s:iterator value="shows">
	  <p>show is: <s:property value="name"/></p>
	</s:iterator>
	
	<s:property value="helloCount" />
  </body>
</html>
