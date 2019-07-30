package problem6.dto;

import java.sql.Date;

/**
 * 今日一日の各運勢の件数を検索するときに使用する外部SQLファイルに値を埋め込むためのパラメータクラス
 * @author k_oda
 *
 */
public class GetFortuneCountDuringTodayParam {

	/**
	 * 今日の日付
	 */
	public Date today;

	/**
	 * 運勢コード
	 */
	public int fortuneId;

}
