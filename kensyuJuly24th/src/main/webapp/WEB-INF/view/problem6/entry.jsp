<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	<meta charset="UTF-8">
	<title>情報登録画面</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
		<h2>お客様情報登録画面</h2>
		<html:messages id="msg" message = "false">
		<div class = "errors">
		<bean:write name = "msg" ignore = "true" filter = "false"/>
		</div>
		</html:messages>
		<p>情報を入力してください</p>
		<script type="text/javascript" src = "/kensyuJuly24th/js/postal.js"></script>
		<ul>
		<s:form action = "/entry/check/" method = "POST">
			<li>氏名(ニックネーム可)<br><html:text property="name"/></li>
			<li>郵便番号(ハイフン無し7桁)<br><html:text property="postalCode" styleId = "postalCode"/></li>
			<li>都道府県<br><html:text property="prefecture" styleId = "prefecture"/></li>
			<li>市区町村<br><html:text property="city" styleId = "city"/></li>
			<li>番地以降<br><html:text property="addressDetail" styleId = "addressDetail"/>
			<button type="button" id = "ajax" class = "select_postal_button">郵便番号検索</button></li>
			<li>電話番号(ハイフン無し)<br><html:text property="phoneNumber"/></li>
			<li>メールアドレス<br><html:text property="mail"/></li>

			<html:hidden property="strToday" value = "${f:h(strToday)}"/>
			<html:hidden property="birthday" value = "${f:h(birthday)}"/>
			<html:hidden property="fortuneName" value = "${f:h(fortuneName)}"/>
			<html:hidden property="negaigoto" value = "${f:h(negaigoto)}"/>
			<html:hidden property="akinai" value = "${f:h(akinai)}"/>
			<html:hidden property="gakumon" value = "${f:h(gakumon)}"/>

			<input type = "submit" value = "確認画面へ" class = "check_button"/>
		</s:form>
		</ul>

		<s:form action = "/getOmikuji/getOmikuji/">
			<html:hidden property="birthday" value = "${f:h(birthday)}"/>
			<input type = "submit" value = "結果画面へ戻る" class = "omikuji_button"/>
		</s:form><br>


	</body>
</html>