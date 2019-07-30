package problem6.dto;

import java.sql.Date;

/**
 * 過去半年間の入力誕生日の全結果を検索する外部SQLファイルの検索結果を受け取るクラス
 * @author k_oda
 *
 */
public class GetResultForHalfAYearFindByBirthdayDto {

	/**
	 * 占い日
	 */
	public Date fortuneDay;

	/**
	 * 運勢名
	 */
	public String fortuneName;

	/**
	 * 願い事
	 */
	public String negaigoto;

	/**
	 * 商い
	 */
	public String akinai;

	/**
	 * 学問
	 */
	public String gakumon;

}
