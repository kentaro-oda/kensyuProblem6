package problem6.action;

import java.sql.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import problem6.dto.OmikujiBean;
import problem6.form.DaysForm;
import problem6.service.OmikujiService;
/**
 * 過去半年間の入力誕生日の結果を返すアクションクラス
 *
 * @author k_oda
 *
 */
public class GetListAction {

	/**
	 * 結果画面から送られてくるアクションフォーム DaysFormをDI
	 */
	@Resource
	@ActionForm
	public DaysForm daysForm;

	/**
	 * 過去半年間の入力誕生日の占い結果を検索するためOmikujiServiceをDI
	 */
	@Resource
	public OmikujiService omikujiService;

	/**
	 * メソッド内で使われるフィールド
	 *
	 * today,dayOfHalfAYearAgo,sqlBirthday		daysFormから送られてくる値をDate型に変換するフィールド
	 * resultForHalfAYearMap	キーに占い実行日、バリューに占いの結果(運勢名、願い事、商い、学問)を格納したマップ
	 *
	 */
	public Date today;
	public Date dayOfHalfAYearAgo;
	public Date sqlBirthday;
	public Map<Date, OmikujiBean> resultForHalfAYearMap;

	/**
	 * 過去半年間の入力誕生日の結果を検索するメソッド
	 *
	 * @return	/problem6/list.jsp	検索した結果を表示させる画面
	 */
	@Execute(validator = false)
	public String getList() {

		/**
		 * アクションフォームの情報をDate型に変換し取得
		 */
		today = Date.valueOf(daysForm.strToday);
		dayOfHalfAYearAgo = Date.valueOf(daysForm.strDayOfHalfAYearAgo);
		sqlBirthday = Date.valueOf(daysForm.birthday);

		/**
		 * 過去半年間の入力誕生日の占い結果を検索
		 */
		resultForHalfAYearMap = omikujiService.getresultForHalfAYear
				(sqlBirthday, dayOfHalfAYearAgo, today);

		return "/problem6/list.jsp";
	}

}
