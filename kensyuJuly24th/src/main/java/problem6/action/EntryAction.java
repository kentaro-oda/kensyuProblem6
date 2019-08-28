package problem6.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.RequestUtil;
import org.seasar.struts.util.ResponseUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import problem6.dto.PostalBean;
import problem6.entity.Postal;
import problem6.form.EntryForm;
import problem6.service.PostalService;

/**
 * 個人情報入力時の処理や結果をメール送信するときに用いるアクションクラス
 * @author k_oda
 *
 */
public class EntryAction {

	//個人情報入力画面から送られてくるアクションフォーム EntryFormをDI
	@Resource
	@ActionForm
	public EntryForm entryForm;

	//郵便番号から住所、またはその逆を非同期通信で検索するためのPostalServiceをDI
	@Resource
	public PostalService postalService;

	/*
	 * メソッド内で使われるフィールド
	 * postalBeanList	サブウィンドウに住所もしくは郵便番号を表示させる時に使用するリスト
	 * postalCode、addressAll、phoneNumber、確認画面で表示する郵便番号、住所、電話番号、メールアドレス
	 */
	public List<PostalBean> postalBeanList;
	public String postalCode;
	public String addressAll;
	public String phoneNumber;

	/**
	 * 個人情報登録画面へ遷移するメソッド
	 *
	 * @return	/problem6/entry.jsp	個人情報入力画面
	 */
	@Execute(validator = false)
	public String index() {
		return "/problem6/entry.jsp";
	}

	/**
	 * 郵便番号から住所を取得する非同期通信メソッド
	 *
	 * @return null	nullを返すことで非同期通信してpostal.jsに戻る
	 */
	@Execute(validator = false)
	public String selectAddress(){

		try {
			//リクエストを取得し、そのリクエストを使ってパラメータから郵便番号を取得
			HttpServletRequest httpServletRequest = RequestUtil.getRequest();
			String posCode = httpServletRequest.getParameter("postalCode");

			/*
			 * DBには先頭が0以外の数字になるまで0が省略されて登録されている(5桁や6桁で登録されているものもある)ので、
			 * 一度int型に変換して先頭の0を排除してから改めてString型に変換して検索に用いる郵便番号とする
			 */
			int postInt = Integer.parseInt(posCode);
			String post = String.valueOf(postInt);

			//郵便番号から住所を取得
			List<Postal> postalList = postalService.getAddressAllFindByPostalCode(post);

			//レスポンスを取得し、jsonファイルをセット、書き込む用のPrintWriterも取得
			HttpServletResponse response = ResponseUtil.getResponse();
			response.setContentType("postal/json");
			PrintWriter sendPoint = new PrintWriter(response.getOutputStream());

			//jsonファイルに書き込むためのObjectMapperを作成し書き込み
			ObjectMapper objectMapper = new ObjectMapper();
			sendPoint.println(objectMapper.writeValueAsString(postalList));
			sendPoint.flush();
			sendPoint.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 郵便番号から検索した時に複数件ヒットした場合、その全てをサブウィンドウに表示させるメソッド
	 *
	 * @return /problem6/chooseAddress.jsp	住所選択画面
	 */
	@Execute(validator = false)
	public String chooseAddress() {

		//リクエストを取得し、そのリクエストを使ってパラメータから住所を取得(都道府県名,市区町村名,番地以降:・・・という風になっている)
		HttpServletRequest httpServletRequest = RequestUtil.getRequest();
		String paramPostalList = httpServletRequest.getParameter("data");

		//「：」ごとに区切り、塊を抽出
		String[] strPostalList = paramPostalList.split(":");

		//画面に表示させるためにリストを作成
		postalBeanList = new ArrayList<>();

		//配列から塊を一つずつ取り出すループ
		for(String strPostal : strPostalList) {

			//都道府県名、市区町村名、番地以降に区切る
			String[] strPostals = strPostal.split(",");

			//PostalBean型変数を作成し、それに分割した項目をセットし最後にリストに追加
			PostalBean postalBean = new PostalBean();
			postalBean.setPrefecture(strPostals[0]);
			postalBean.setCity(strPostals[1]);
			postalBean.setAddressDetail(strPostals[2]);
			postalBeanList.add(postalBean);
		}
		return "/problem6/chooseAddress.jsp";
	}

	/**
	 * サブウィンドウで選択した住所をメインウィンドウに表示させるメソッド
	 *
	 * @return null	nullを返すことで非同期通信にしてchooseAddres.jsに戻る
	 */
	@Execute(validator = false)
	public String decideAddress() {
		try {
			//リクエストを取得し、そのリクエストを使ってパラメータから住所を取得し都道府県名、市区町村名、番地以降に分割
			HttpServletRequest httpServletRequest = RequestUtil.getRequest();
			String addressAll = httpServletRequest.getParameter("addressAll");
			String[] addresses = addressAll.split(",");

			//Postal型の変数を作成し、それに都道府県名、市区町村名、番地以降を登録
			Postal postal = new Postal();
			postal.prefecture = addresses[0];
			postal.city = addresses[1];
			postal.addressDetail = addresses[2];

			//レスポンスを取得し、jsonファイルをセット、書き込む用のPrintWriterも取得
			HttpServletResponse response = ResponseUtil.getResponse();
			response.setContentType("postal/json");
			PrintWriter sendPoint = new PrintWriter(response.getOutputStream());

			//jsonファイルに書き込むためのObjectMapperを作成し書き込み
			ObjectMapper objectMapper = new ObjectMapper();
			sendPoint.println(objectMapper.writeValueAsString(postal));
			sendPoint.flush();
			sendPoint.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 住所から郵便番号を取得する非同期通信メソッド
	 *
	 * @return	null	nullを返すことで非同期にしてpostal.jsに戻る
	 */
	@Execute(validator = false)
	public String selectPostalCode() {

		try {
			//リクエストを取得し、そのリクエストを使ってパラメータから住所(都道府県名、市区町村名、番地以降)を取得
			HttpServletRequest httpServletRequest = RequestUtil.getRequest();
			String prefecture = httpServletRequest.getParameter("prefecture");
			String city = httpServletRequest.getParameter("city");
			String addressDetail = httpServletRequest.getParameter("addressDetail");

			//「が」、「の」の種類の配列と置き換え文字の配列を作成
			Character[] gaList = {'が', 'ガ', 'ヶ', 'ケ'};
			Character[] noList = {'の', 'ノ', '乃', '之', '野'};
			Character[] escaper = {'¥', '$'};

			/*
			 * 番地以降が６文字以上の場合５文字目までを抽出
			 * (番地以降の住所を最後まで正しく入力していた場合、検索に引っかからなくなるのを防ぐためと
			 *  ５文字目以降に「が」や「の」が含まれていた場合に処理を省けるのでここで一旦抽出を入れる)
			 */
			if(addressDetail.length() > 6) {
				addressDetail = addressDetail.substring(0,5);
			}

			//市区町村名と番地以降の住所に対して「が」系統と「の」系統が含まれているかを確認し検索用の市区町村名と番地以降の住所を作成
			String cityEscaper = postalService.checkCharacterInCity(gaList, noList, city, escaper[0], escaper[1]);
			String addressDetailEscaper = postalService.checkCharacterInAddressDetail(gaList, noList, addressDetail, escaper[0], escaper[1]);


			//検索結果を格納するリストを作成し検索
			List<Postal> postalList = new ArrayList<>();

			//文字列抽出末尾のカウンタ
			int i = 5;

			/*検索用の番地以降の値を５文字からどんどん減らしていき１件でもヒット、
			 * もしくはiが０(未字数を減らしていき１文字目だけで検索しても結果が出ない)になったらループを抜ける処理
			 */
			do {
				//検索用の番地以降がi文字以上の場合、i文字目までを抽出(if文がないとi文字未満の場合エラーになってしまう)
				if(addressDetailEscaper.length() > i) {
					addressDetailEscaper = addressDetailEscaper.substring(0, i);
				}

				//検索結果をリストに追加しカウンタを１減らす
				postalList.addAll(postalService.getPostalCodeFindByAddresses(prefecture, cityEscaper, addressDetailEscaper));
				i--;
			}while(postalList.size() == 0 && i != 0);

			//検索でヒットが一件もない時の再検索
			if(postalList.size() <= 0) {

				/*
				 * addressDetailEscaperを「場合」に変更して再検索
				 * (これにより、「以下に検索がない場合」と「〜の次に番地が来る場合」を引っ張ってくる)
				 */
				addressDetailEscaper = "場合";
				postalList.addAll(postalService.getPostalCodeFindByAddresses(prefecture, cityEscaper, addressDetailEscaper));

			}


			//郵便番号を7桁に調節
			for(Postal postal : postalList) {
				postal.postalCode = postalService.adjustPostalCodeLength(postal.postalCode);
			}
			//レスポンスを取得し、jsonファイルをセット、書き込む用のPrintWriterも取得
			HttpServletResponse response = ResponseUtil.getResponse();
			response.setContentType("postal/json");
			PrintWriter sendPoint = new PrintWriter(response.getOutputStream());

			//jsonファイルに書き込むためのObjectMapperを作成し書き込み
			ObjectMapper objectMapper = new ObjectMapper();
			sendPoint.println(objectMapper.writeValueAsString(postalList));
			sendPoint.flush();
			sendPoint.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//nullを返すことで非同期通信にする
		return null;
	}

	/**
	 * 住所から検索した時に複数件ヒットした場合、その全てをサブウィンドウに表示させるメソッド
	 *
	 * @return /problem6/choosePostalCode.jsp	郵便番号選択画面
	 */
	@Execute(validator = false)
	public String choosePostalCode() {

		//リクエストを取得し、そのリクエストを使ってパラメータから郵便番号と番地以降の住所を取得(郵便番号,番地以降:・・・となっている)
		HttpServletRequest httpServletRequest = RequestUtil.getRequest();
		String postalList = httpServletRequest.getParameter("data");

		//各郵便番号ごとに情報を分割
		String[] strPostalList = postalList.split(":");

		//画面に表示させるためにリストを作成
		postalBeanList = new ArrayList<>();

		//配列から塊を一つずつ取り出すループ
		for(String postalCodes : strPostalList) {

			//郵便番号と番地以降に分割
			String[] postalCodeList = postalCodes.split(",");

			//PostalBean型変数を作成し、郵便番号と番地以降をセットしてリストに格納
			PostalBean postalBean = new PostalBean();
			postalBean.setPostalCode(postalCodeList[0]);
			postalBean.setAddressDetail(postalCodeList[1]);
			postalBeanList.add(postalBean);
		}
		return "/problem6/choosePostalCode.jsp";
	}

	/**
	 * サブウィンドウで選択した郵便番号をメインウィンドウに表示させるメソッド
	 *
	 * @return null	nullを返すことで非同期通信にしてchooseAddres.jsに戻る
	 */
	@Execute(validator = false)
	public String decidePostalCode() {
		try {

			//リクエストを取得し、そのリクエストを使ってパラメータから郵便番号を取得
			HttpServletRequest httpServletRequest = RequestUtil.getRequest();
			String postalCode = httpServletRequest.getParameter("postalCode");

			//レスポンスを取得し、jsonファイルをセット、書き込む用のPrintWriterも取得
			HttpServletResponse response = ResponseUtil.getResponse();
			response.setContentType("postal/json");
			PrintWriter sendPoint = new PrintWriter(response.getOutputStream());

			//jsonファイルに書き込むためのObjectMapperを作成し書き込み
			ObjectMapper objectMapper = new ObjectMapper();
			sendPoint.println(objectMapper.writeValueAsString(postalCode));
			sendPoint.flush();
			sendPoint.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 入力チェックを行うメソッド(入力失敗時は個人情報入力画面に戻る)
	 *
	 * @return	/entry/dispInformation/	入力情報の確認メソッド
	 */
	@Execute(validator = true, input = "/problem6/entry.jsp")
	public String check() {
		return "/entry/dispInformation/";
	}

	/**
	 * 入力情報の確認メソッド(郵便番号、住所の各項目、電話番号に作用)
	 * 未入力の場合、「未登録」と表示されるように代入
	 * 入力されている場合、そのまま表示(住所の部分だけ文字列連結をさせる)
	 *
	 * @return	/problem6/entryCheck.jsp	情報確認画面
	 */
	@Execute(validator = false)
	public String dispInformation() {
		if(entryForm.postalCode == null || entryForm.postalCode.isEmpty()) {
			postalCode = "未登録";
		} else {
			postalCode = entryForm.postalCode;
		}

		if(entryForm.prefecture == null || entryForm.prefecture.isEmpty()) {
			addressAll = "未登録";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(entryForm.prefecture);
			sb.append(entryForm.city);
			sb.append(entryForm.addressDetail);
			addressAll = sb.toString();
		}

		if(entryForm.phoneNumber == null || entryForm.phoneNumber.isEmpty()) {
			phoneNumber = "未登録";
		} else {
			phoneNumber = entryForm.phoneNumber;
		}

		return "/problem6/entryCheck.jsp";
	}

	/**
	 * メールを送信するメソッド
	 *
	 * @return	/problem6/entryFinished.jsp	完了画面
	 */
	@Execute(validator = false)
	public String entry() {
		//メール送信のためのパラメータを設定
		String to = entryForm.mail;
		String from = "server@seasar.com";
		String host = "192.168.1.1";

		//メール送信時のデバッグ設定用変数を定義(これの必要性)
		boolean debug = Boolean.valueOf(true).booleanValue();

		//メール送信のためのプロパティを設定
		Properties properties = new Properties();
		properties.put("mail.debug", host);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		//デバッグ設定を行う
		if(debug) {
			properties.put("mail.debug", Boolean.valueOf(debug));
		}

		//メール送信のためのセッションを取得する
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kentaro.oda@bananadream.co.jp", "HTxc8554");
			}
		});
		session.setDebug(debug);

		try {
			//メールを送信するための各種設定
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));//送信元メールアドレスを設定
			InternetAddress[] address = {new InternetAddress(to)};
			message.setRecipients(Message.RecipientType.TO, address);//送信先メールアドレスを設定
			message.setSubject("占い結果", "ISO-2022-JP");//メール件名を設定
			message.setSentDate(new Date());//メール送信日を設定
			message.setText(postalService.mailText(), "ISO-2022-JP");//メール本文を設定

			//メールを送信する(送信時にgmailのアカウントのセキュリティレベルを落とす)
			Transport.send(message);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		return "/problem6/entryFinished.jsp";
	}


}
