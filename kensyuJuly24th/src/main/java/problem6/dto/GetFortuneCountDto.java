package problem6.dto;

/**
 * 各運勢の件数を求めるときに使う外部SQLファイルの結果を受け取るDTO(半年間、当日共通)
 *
 * @author k_oda
 *
 */
public class GetFortuneCountDto {

	//運勢名
	public String fortuneName;

	//件数(行数)
	public Long count;
}
