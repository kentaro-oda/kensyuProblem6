<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	<meta charset="UTF-8">
	<title>誕生日入力画面</title>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
		<h2>誕生日占い</h2>
		<html:messages id="msg" message = "false">
		<div class = "errors">
		<bean:write name = "msg" ignore = "true" filter = "false"/>
		</div>
		</html:messages>
		<p>生年月日を入力してください(例：2019-07-11)</p>
		<s:form action = "/inputBirthday/check/" method = "POST">
		<html:text property = "birthday" />
		<input type = "submit" value = "結果" class = "omikuji_button"/>
		</s:form>
	</body>
</html>