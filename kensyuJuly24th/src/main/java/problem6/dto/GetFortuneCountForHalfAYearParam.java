package problem6.dto;

import java.sql.Date;

/**
 * 過去半年間の各運勢の件数を求めるときに使う外部SQLファイルに値を埋め込むためのパラメータクラス
 *
 * @author k_oda
 *
 */
public class GetFortuneCountForHalfAYearParam {

	//半年前の日付
	public Date dayOfHalfAYearAgo;

	//今日の日付
	public Date today;

	//運勢コード
	public int fortuneId;
}
