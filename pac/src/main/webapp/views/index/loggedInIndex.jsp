<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
	<link rel="stylesheet" href="/pac/css/vendor/spectre/spectre.min.css">
	<link rel="stylesheet" href="/pac/css/vendor/spectre/spectre-exp.min.css">
	<link rel="stylesheet" href="/pac/css/vendor/spectre/spectre-icons.min.css">
	<link rel="stylesheet" href="/pac/css/navbar.css">
	<link rel="stylesheet" href="/pac/css/styles.css">
	
	
	<sx:head/>
	
	<head>
		<meta charset="UTF-8">
		<title>Middle Township Performing Arts Center</title>
	</head>
	<body>
		<div id="overview" class="container grid-hero grid-lg text-center">
			<header class="navbar">
				<section class="navbar-section">
					<h1>MTHSPac</h1>
				</section>
				<section class="navbar-section">
					<a href="/pac/index" class="btn btn-link darkgreen">Shows</a> 
					<a href="/pac/views/shows/addshow.jsp" class="btn btn-link darkgreen">Add Show</a>
					<a href="/pac/profile" class="btn btn-link darkgreen">Profile</a>
					<a href="/pac/logout" class="btn btn-link darkgreen">Logout</a> 
				</section>
			</header>
			<div class="bar"></div>
	       
			<s:if test="%{msg != null}">
				<div id="msgDiv" class="toast toast-success spacing">
					<button onclick="document.getElementById('msgDiv').style.display = 'none'" class="btn btn-clear float-right"></button>
					<s:property value="msg" />
				</div>
			</s:if>
			<s:if test="%{errorMsg != null}">
				<div id="errorDiv" class="toast toast-error spacing">
					<button onclick="document.getElementById('errorDiv').style.display = 'none'" class="btn btn-clear float-right"></button>
					<s:property value="errorMsg" />
				</div>
			</s:if>
	       