package problem6.service;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static problem6.entity.PostalNames.*;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;

import problem6.entity.Postal;
import problem6.form.EntryForm;

/**
 * 住所テーブルに関するServiceクラス
 * AbstractServiceを継承
 *
 * @author k_oda
 *
 */
public class PostalService extends AbstractService<Postal>{

	//個人情報のアクションフォーム EntryFormをDI
	@Resource
	@ActionForm
	public EntryForm entryForm;

	/**
	 * 郵便番号から住所を検索するメソッド
	 *
	 * @param postalCode	郵便番号
	 * @return	postalList/検索された住所のリスト(郵便番号が被っているものもあるためリストで返却)
	 */
	public List<Postal> getAddressAllFindByPostalCode(String postalCode){
		List<Postal> postalList = jdbcManager.from(Postal.class).where(eq(postalCode(), postalCode)).orderBy(asc(id())).getResultList();

		return postalList;
	}

	/**
	 * 市区町村名に「が」系統と「の」系統が含まれているかを判定し、文字を置き換えるメソッド
	 *
	 * @param gaList	「が」系統が格納された配列
	 * @param noList	「の」系統が格納された配列
	 * @param city		市区町村名
	 * @param escaper¥	「が」系統を置き換える文字
	 * @param escaper$	「の」系統を置き換える文字
	 *
	 * @return cityEscaper	文字の置き換えが終わった市区町村名
	 */
	public String checkCharacterInCity(Character[] gaList, Character[] noList, String city, Character escaper¥,Character escaper$){

		//検索用市区町村名に市区町村名を書き写す
		String cityEscaper = city;

		//「が」系統の配列を回す
		for(Character ga : gaList) {

			//「が」が含まれているかを判定(「ガ、ヶ、ケ」も順に確認)
			if(cityEscaper.contains(ga.toString())) {
				//文字の置き換え
				cityEscaper = cityEscaper.replace(ga, escaper¥);
			}
		}

		//の」系統の配列を回す
		for(Character no : noList) {

			//「の」が含まれているかを判定(「ノ、乃、之、野」も順に確認)
			if(cityEscaper.contains(no.toString())) {
				//文字の置き換え
				cityEscaper = cityEscaper.replace(no, escaper$);
			}
		}
		return cityEscaper;
	}

	/**
	 * 番地以降の住所に「が」系統と「の」系統が含まれているかを判定し、文字を置き換えるメソッド
	 *
	 * @param gaList		「が」系統が格納された配列
	 * @param noList		「の」系統が格納された配列
	 * @param addressDetail	 番地以降の住所
	 * @param escaper¥		「が」系統を置き換える文字
	 * @param escaper$		「の」系統を置き換える文字
	 *
	 * @return addressDetailEscaper	文字の置き換えが終わった番地以降の住所
	 */
	public String checkCharacterInAddressDetail(Character[] gaList, Character[] noList, String addressDetail, Character escaper¥, Character escaper$){

		//検索用の住所名に番地以降の住所をコピー
		String addressDetailEscaper = addressDetail;

		//「が」系統の配列を回す
		for(Character ga: gaList) {

			//「が」が含まれているかを判定(「ガ、ヶ、ケ」も順に確認)
			if(addressDetailEscaper.contains(ga.toString())) {
				//文字の置き換え
				addressDetailEscaper = addressDetailEscaper.replace(ga, escaper¥);
			}
		}

		//「の」系統の配列を回す
		for(Character no: noList) {

			//「の」が含まれているかを判定(「ノ、乃、之、野」も順に確認)
			if(addressDetailEscaper.contains(no.toString())) {
				//文字の置き換え
				addressDetailEscaper = addressDetailEscaper.replace(no, escaper$);
			}
		}

		return addressDetailEscaper;
	}

	/**
	 * 住所から郵便番号を検索するメソッド
	 *
	 * @param prefecture			都道府県名
	 * @param cityEscaper			検索用市区町村名
	 * @param addressDetailEscaper	検索用の番地以降の住所
	 * @return	postalList/検索された郵便番号のリスト(郵便番号が被っているものもあるためリストで返却)
	 */
	public List<Postal> getPostalCodeFindByAddresses(String prefecture, String cityEscaper, String addressDetailEscaper){
		List<Postal> postalList = jdbcManager.from(Postal.class).where(
				contains(prefecture(), prefecture),
				contains(cityEscaper(), cityEscaper),
				contains(addressDetailEscaper(), addressDetailEscaper)).orderBy(asc(id()))
				.getResultList();

		return postalList;
	}

	/**
	 * 郵便番号の桁数を7桁にするメソッド
	 *
	 * @param postalCode	郵便番号
	 * @return	perfectPostalCode/7桁になった郵便番号
	 */
	public String adjustPostalCodeLength(String postalCode) {

		String perfectPostalCode = postalCode;

		while(perfectPostalCode.length() < 7) {
			StringBuilder sb = new StringBuilder();
			sb.append(0);
			sb.append(perfectPostalCode);
			perfectPostalCode = sb.toString();
		}
		return perfectPostalCode;
	}

	/**
	 * メールの本文を作成するメソッド
	 *
	 * @return sb.toString()	完成したメールの本文
	 */
	public String mailText() {
		StringBuilder sb = new StringBuilder();
		sb.append(entryForm.name);
		sb.append("の今回の占いの結果です。\n");
		sb.append("お誕生日：");
		sb.append(this.convertBirthday());
		sb.append("\n運勢：");
		sb.append(entryForm.fortuneName);
		sb.append("\n願い事：");
		sb.append(entryForm.negaigoto);
		sb.append("\n商い：");
		sb.append(entryForm.akinai);
		sb.append("\n学問：");
		sb.append(entryForm.gakumon);

		return sb.toString();
	}

	/**
	 * 生年月日をyyyy-MM-ddの形式からy年M月d日に変換するメソッド
	 *
	 * @return sb.toString()	y年M月d日に変換された生年月日
	 */
	private String convertBirthday() {
		//yyyy-MM-ddを-区切りで年、月、日に分割して配列に格納
		String[] str = entryForm.birthday.split("-");

		//年、月、日をそれぞれ無駄な0を省く
		str[0] = this.convertYear(str[0]);
		str[1] = this.convertMonthOrDate(str[1]);
		str[2] = this.convertMonthOrDate(str[2]);

		//変換し終えた年月日をy年M月d日の形に文字列連結
		StringBuilder sb = new StringBuilder();
		sb.append(str[0]);
		sb.append("年");
		sb.append(str[1]);
		sb.append("月");
		sb.append(str[2]);
		sb.append("日");

		return sb.toString();

	}

	/**
	 * 西暦の桁数を調節するメソッド(先頭が0以外の数字になるまで0を削除するメソッド、例：0023→23)
	 *
	 * @param year	西暦
	 * @return year	変換された西暦
	 */
	private String convertYear(String year) {
		//千の位が0かどうかの判定
		if((year.substring(0, 1)).equals("0")) {

			//百の位が0かどうかの判定
			if((year.substring(1, 2)).equals("0")) {

				//十の位が0かどうかの判定
				if((year.substring(2, 3)).equals("0")) {
					//一桁の場合の西暦の摘出
					year = year.substring(3, 4);
				} else {
					//二桁の場合の西暦の摘出
					year = year.substring(2, 4);
				}
			} else {
				//三桁の場合の西暦の摘出
				year = year.substring(1, 4);
			}
		}
		return year;
	}

	/**
	 * 月または日から先頭の0をとるメソッド
	 *
	 * @param mod	月もしくは日
	 * @return	mod	変換された月もしくは日
	 */
	private String convertMonthOrDate(String mod) {
		//十の位が0だったら一の位の数のみを摘出
		if((mod.substring(0, 1)).equals("0")) {
			mod = mod.substring(1, 2);
		}
		return mod;
	}
}
