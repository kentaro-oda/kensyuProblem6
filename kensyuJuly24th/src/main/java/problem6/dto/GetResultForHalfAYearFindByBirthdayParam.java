package problem6.dto;

import java.sql.Date;

/**
 * 過去半年間の入力誕生日の結果を検索する外部SQLファイルに値を埋め込むためのパラメータクラス
 * @author k_oda
 *
 */
public class GetResultForHalfAYearFindByBirthdayParam {

	/**
	 * 誕生日
	 */
	public Date sqlBirthday;

	/**
	 * 半年前の日付
	 */
	public Date dayOfHalfAYearAgo;

	/**
	 * 今日の日付
	 */
	public Date today;

}
