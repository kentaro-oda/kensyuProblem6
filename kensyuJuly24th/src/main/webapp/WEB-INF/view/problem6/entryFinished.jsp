<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	<meta charset="UTF-8">
	<title>送信完了画面</title>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
		<h2>メールを送信しました！</h2>

		<s:form action = "/getOmikuji/getOmikuji/">
			<html:hidden property="birthday" value = "${f:h(birthday)}"/>
			<input type = "submit" value = "結果画面へ戻る" class = "omikuji_button"/>
		</s:form><br>

		<a href = "/kensyuJuly24th/inputBirthday/">トップへ戻る</a>

	</body>
</html>