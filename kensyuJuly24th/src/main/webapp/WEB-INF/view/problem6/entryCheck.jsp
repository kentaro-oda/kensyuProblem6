<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	<meta charset="UTF-8">
	<title>入力情報確認画面</title>
	<link rel = "stylesheet" href = "/kensyuJuly24th/style/stylesheet.css"/>
	</head>
	<body>
		以下の内容で登録します。よろしいですか？<br>
		<ul>
		<li>
			お名前<br>
			${f:h(name)}
		</li>
		<li>
			郵便番号<br>
			${f:h(postalCode)}
		</li>
		<li>
			住所<br>
			${f:h(addressAll)}
		</li>
		<li>
			電話番号<br>
			${f:h(phoneNumber)}
		</li>
		<li>
			メールアドレス<br>
			${f:h(mail)}
		</li>
		</ul>

		<s:form action = "/entry/entry/" method = "POST">
			<html:hidden property="name" value = "${f:h(name)}"/>
			<html:hidden property="postalCode" value = "${f:h(postalCode)}"/>
			<html:hidden property="addressAll" value = "${f:h(addressAll)}"/>
			<html:hidden property="phoneNumber" value = "${f:h(phoneNumber)}"/>
			<html:hidden property="mail" value = "${f:h(mail)}"/>

			<html:hidden property="strToday" value = "${f:h(strToday)}"/>
			<html:hidden property="birthday" value = "${f:h(birthday)}"/>
			<html:hidden property="fortuneName" value = "${f:h(fortuneName)}"/>
			<html:hidden property="negaigoto" value = "${f:h(negaigoto)}"/>
			<html:hidden property="akinai" value = "${f:h(akinai)}"/>
			<html:hidden property="gakumon" value = "${f:h(gakumon)}"/>
			<input type = "submit" value = "送信" class = "complete_button"/>
		</s:form>

		<s:form action = "/entry/" method = "POST">

		<html:hidden property="name" value = "${f:h(name)}"/>
		<html:hidden property="postalCode" value = "${f:h(postalCode)}"/>
		<html:hidden property="prefecture" value = "${f:h(prefecture)}"/>
		<html:hidden property="city" value = "${f:h(city)}"/>
		<html:hidden property="addressDetail" value = "${f:h(addressDetail)}"/>
		<html:hidden property="phoneNumber" value = "${f:h(phoneNumber)}"/>
		<html:hidden property="mail" value = "${f:h(mail)}"/>
		<html:hidden property="strToday" value = "${f:h(strToday)}"/>
		<html:hidden property="birthday" value = "${f:h(birthday)}"/>
		<html:hidden property="fortuneName" value = "${f:h(fortuneName)}"/>
		<html:hidden property="negaigoto" value = "${f:h(negaigoto)}"/>
		<html:hidden property="akinai" value = "${f:h(akinai)}"/>
		<html:hidden property="gakumon" value = "${f:h(gakumon)}"/>

		<input type = "submit" value = "戻る" class = "regist_button"/>
		</s:form>
	</body>
</html>