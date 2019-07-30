<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>過去半年間の入力誕生日の結果</title>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
		<h2>過去半年間の入力誕生日の結果</h2>
		<table class = "list" border = "1">
		<tr>
			<th>占い日</th>
			<th>運勢</th>
			<th>願い事</th>
			<th>商い</th>
			<th>学問</th>
		</tr>

		<!-- マップの行数だけ繰り返し表示させる -->
		<c:forEach var = "omikujiMap" items = "${resultForHalfAYearMap}">

		<tr>
			<td>${omikujiMap.key}</td>
			<td>${omikujiMap.value.fortune.fortuneName}</td>
			<td>${omikujiMap.value.negaigoto}</td>
			<td>${omikujiMap.value.akinai}</td>
			<td>${omikujiMap.value.gakumon}</td>

		</tr>
		</c:forEach>

		</table><br>
		<s:form action = "/getOmikuji/getOmikuji/">
			<html:hidden property="birthday" value = "${birthday}"/>
			<input type = "submit" value = "結果画面へ戻る" class = "omikuji_button"/>
		</s:form><br>

		<a href = "/kensyuJuly24th/inputBirthday/">トップへ戻る</a>
	</body>
</html>