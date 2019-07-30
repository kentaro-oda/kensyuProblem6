<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>おみくじ結果</title>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
		<h2>今日の結果</h2>
		<table class = "omikuji">
			<tr>
			<td colspan = "2">今日の運勢は${f:h(omikujiBean.fortune.fortuneName)}です。</td>
			</tr>
			<tr>
			<th>願い事：</th>
			<td>${f:h(omikujiBean.negaigoto)}</td>
			</tr>
			<tr>
			<th>商　い：</th>
			<td>${f:h(omikujiBean.akinai)}</td>
			</tr>
			<tr>
			<th>学　問：</th>
			<td>${f:h(omikujiBean.gakumon)}</td>
			</tr>
		</table><br>

		<div class = "side_by_side">
		<s:form action = "/getRate/getRate/" method = "POST">
			<html:hidden property="strToday" value = "${f:h(strToday)}"/>
			<html:hidden property="strDayOfHalfAYearAgo" value = "${f:h(strDayOfHalfAYearAgo)}"/>
			<html:hidden property="birthday" value = "${f:h(birthday)}"/>
			<input type = "submit" value = "過去半年と今日の運勢の割合" class = "rate_button"/>
		</s:form>

		<s:form action = "/getList/getList/">
			<html:hidden property="strToday" value = "${f:h(strToday)}"/>
			<html:hidden property="strDayOfHalfAYearAgo" value = "${f:h(strDayOfHalfAYearAgo)}"/>
			<html:hidden property="birthday" value = "${f:h(birthday)}"/>
			<input type = "submit" value = "過去半年間のあなたの運勢" class = "list_button left_margin"/>
		</s:form>
		</div>

		<div class = "top_margin">
		<a href = "/kensyuJuly24th/inputBirthday/">トップへ戻る</a>
		</div>

	</body>
</html>