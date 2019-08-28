//郵便番号から住所を引く非同期通信
$(function(){
	$('#postalCode').on('blur', function(){
		$.ajax({
			url:'selectAddress/',
			type:'POST',
			dataType:'json',
			data:{
				'postalCode':$('#postalCode').val()
			}
		})

		.done(function(data){
			//検索結果が複数の場合(郵便番号重複地域だった場合)
			if(data.length >= 2){
				//1件目の情報を登録し、ループカウンタを作成
				var sendingData = data[0].prefecture+ ',' + data[0].city + ',' + data[0].addressDetail + ':';
				var i = 1;

				/*
				 * 情報を全件連結させる(各番ごとに「:」で各項目ごとに「,」で区切る)
				 * ※URLに文字として登録するため(これをしないと各情報が[object, Object]という文字列になってしまう)
				 */
				while(i < data.length){
					sendingData = sendingData + data[i].prefecture + ',' + data[i].city + ',' + data[i].addressDetail;
					i++;
					//続きがある場合に「:」を挿入
					if(i < data.length){
						sendingData = sendingData + ':';
					}
				}
				//連結させた文字列をパラメータとし選択させるサブウィンドウを開くためのアクションメソッドに飛ぶ
				window.open(encodeURI("/kensyuJuly24th/entry/chooseAddress?data="+sendingData));
			}

			//検索結果が１件の場合
			else if(data.length == 1){
				//各IDに値をセット
				$('#prefecture').attr('value', data[0].prefecture);
				$('#city').attr('value', data[0].city);
				$('#addressDetail').attr('value', data[0].addressDetail);
				console.log(data);
			}
			//検索結果が0件の場合
			else {
				alert("郵便番号が正しくありません。\n入力しなおしてください。");
			}
		})

		.fail(function(data){
			alert("郵便番号が正しくありません。\n入力しなおしてください。");
			alert(data.postal.prefecture);
			 console.log(XMLHttpRequest.status);
			    console.log(textStatus);
			    console.log(errorThrown);
		});

	});
});

//住所から郵便番号を引く非同期通信
$(function(){
	$('#ajax').on('click', function(){
		$.ajax({
			url:'selectPostalCode/',
			type:'POST',
			dataType:'json',
			data:{
				'prefecture':$('#prefecture').val(),
				'city':$('#city').val(),
				'addressDetail':$('#addressDetail').val()
			}
		})

		.done(function(data){
			//検索結果が複数の場合(掲載がないパターンや)
			if(data.length >= 2){

				//１件目の情報を登録しループカウンタを作成
				var sendingData = data[0].postalCode + ',' + data[0].addressDetail + ':';
				var i = 1;

				/*
				 * 情報を全件連結させる(各番ごとに「:」で郵便番号と番地以降の間を「,」で区切る)
				 * ※URLに文字として登録するため(これをしないと各情報が[object, Object]という文字列になってしまう)
				 */
				while(i < data.length){
					sendingData = sendingData + data[i].postalCode + ',' + data[i].addressDetail;
					i++;
					//続きがある場合に「:」を挿入
					if(i < data.length){
						sendingData = sendingData + ':';
					}
				}
				//連結させた文字列をパラメータとし選択させるサブウィンドウを開くためのアクションメソッドに飛ぶ
				window.open(encodeURI("/kensyuJuly24th/entry/choosePostalCode?data="+sendingData));
			}

			//検索結果が１件の場合
			else if(data.length == 1){
				//郵便番号をセット
				$('#postalCode').attr('value', data[0].postalCode);
				console.log(data);
			}

			//検索結果が0件の場合
			else {
				alert("該当する住所が見つかりません。\n入力しなおしてください。");
			}
		})

		.fail(function(data){
			alert("該当する住所が見つかりません。\n入力しなおしてください。");
			 console.log(XMLHttpRequest.status);
			    console.log(textStatus);
			    console.log(errorThrown);
		});

	});
});
