//複数ある住所から１件選ぶ非同期通信
$(function(){
	$('#decideAddress').on('click', function(){
		$.ajax({
			url:'decideAddress/',
			type:'POST',
			dataType:'json',
			data:{
				//※「:checked」をつけることによってラジオボタンで選択されたものが正しく渡せる
				'addressAll':$('#addressAll:checked').val()
			}
		})

		.done(function(data){
			//メインウィンドウの各IDの部分に取得した値を登録
			window.opener.document.getElementById('prefecture').value
			=data.prefecture;
			window.opener.document.getElementById('city').value
			=data.city;
			window.opener.document.getElementById('addressDetail').value
			=data.addressDetail;

			//サブウィンドウを閉じる
			window.close();
		})

		.fail(function(data){
		})
	})
})

//複数件ある郵便番号から１件選ぶ非同期通信
$(function(){
	$('#decidePostalCode').on('click', function(){
		$.ajax({
			url:'decidePostalCode/',
			type:'POST',
			dataType:'json',
			data:{
				'postalCode':$('#posCode:checked').val()
			}
		})

		.done(function(data){
			//メインウィンドウに選択した郵便番号を登録し、サブウィンドウを閉じる
			window.opener.document.getElementById('postalCode').value=data;
			window.close();
		})

		.fail(function(data){
		})
	})
})