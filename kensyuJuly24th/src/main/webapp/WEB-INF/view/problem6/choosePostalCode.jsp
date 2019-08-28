<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	<meta charset="UTF-8">
	<title>選択画面</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
	<script type="text/javascript" src = "/kensyuJuly24th/js/decide.js"></script>
		<s:form action = "/entry/decidePostalCode/" method = "POST">
			<c:forEach var = "postal" items = "${postalBeanList}">
			<html:radio property = "postalCode" styleId = "posCode" value = "${postal.postalCode}"/>
			${postal.postalCode}：${postal.addressDetail}<br>
			</c:forEach>
			<button type = "button" id = "decidePostalCode" class = "decide_button">決定</button>
		</s:form>
	</body>
</html>