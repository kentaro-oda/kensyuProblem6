<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>運勢の割合</title>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
		<h2>各運勢の割合</h2>

		<!-- 過去半年間の各運勢の割合の表 -->
		<div class = "side_by_side">
		<table class = "rate" border = "1">
		<tr>
			<th class = "table_size" colspan = "2">半年間の割合</th>
		</tr>
		<c:forEach var = "rateHalfAYear" items = "${fortuneRateForHalfAYearMap}">
			<tr>
			<th>${rateHalfAYear.key}</th>
			<td>${rateHalfAYear.value}％</td>
			</tr>
		</c:forEach>
		</table><br>

		<!-- 当日の各運勢の割合の表 -->
		<table class = "rate left_margin" border = "1">
		<tr>
			<th class = "table_size" colspan = "2">当日の割合</th>
		</tr>
		<c:forEach var = "rateToday" items = "${fortuneRateDuringTodayMap}">
			<tr>
			<th>${rateToday.key}</th>
			<td>${rateToday.value}％</td>
			</tr>
		</c:forEach>
		</table>
		</div><br>

		<div class = "top_margin">
		<s:form action = "/getOmikuji/getOmikuji/">
			<html:hidden property="birthday" value = "${birthday}"/>
			<input type = "submit" value = "結果画面へ戻る" class = "omikuji_button"/>
		</s:form></div><br>

		<a href = "/kensyuJuly24th/inputBirthday/">トップへ戻る</a>
	</body>
</html>