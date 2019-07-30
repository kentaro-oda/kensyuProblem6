package problem6.action;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import problem6.dto.OmikujiBean;
import problem6.entity.Result;
import problem6.form.BirthdayForm;
import problem6.service.OmikujiService;
import problem6.service.ResultService;

/**
 * 入力誕生日からおみくじの結果を取得するアクションクラス
 *
 * @author k_oda
 *
 */
public class GetOmikujiAction {

	/**
	 * 入力誕生日のアクションフォーム BirthdayFormをDI
	 */
	@Resource
	@ActionForm
	protected BirthdayForm birthdayForm;

	/**
	 * 過去に同日・同誕生日でおみくじを引いたか調べる処理と結果を登録する際に用いるためResultServiceをDI
	 */
	@Resource
	protected ResultService resultService;

	/**
	 * おみくじの結果を獲得する際に用いるためOmikujiServiceをDI
	 */
	@Resource
	protected OmikujiService omikujiService;

	/**
	 * メソッド内で使われるフィールド
	 *
	 * sqlBirthday	フォームから送られた誕生日をDate型に変換したもの
	 * calendar		今日の日付と半年前の日付を取得するために使うCalendar型変数
	 * today		今日の日付
	 * dayOfHalfAYearAgo	半年前の日付
	 * strToday,strDayOfHalfAYearAgo	フォーム送信用にString型に変換された今日の日付と半年前の日付
	 * omikujiId	おみくじコード
	 * omikujiBean	おみくじの結果を格納するDTO
	 * result		結果を登録するために必要なResultのエンティティの変数
	 *
	 */
	public Date sqlBirthday;
	public Calendar calendar;
	public Date today;
	public Date dayOfHalfAYearAgo;
	public String strToday;
	public String strDayOfHalfAYearAgo;
	public int omikujiId;
	public OmikujiBean omikujiBean;
	public Result result;

	/**
	 * おみくじの結果を取得するメソッド(割合画面とリスト画面から戻るときにも使用)
	 *
	 * @return	/problem6/omikuji.jsp	おみくじの結果を表示させる画面
	 */
	@Execute(validator = false)
	public String getOmikuji() {
		/**
		 * 入力された誕生日をsql.date型に変換し、今日の日付を取得しつつsqi.Date型に変換
		 */
		sqlBirthday = Date.valueOf(birthdayForm.birthday);
		calendar = Calendar.getInstance();
		today = new  Date(calendar.getTime().getTime());

		/**
		 * 半年前の日付を取得
		 */
		calendar.add(Calendar.MONTH, -6);
		dayOfHalfAYearAgo = new  Date(calendar.getTime().getTime());

		/**
		 * フォーム送信用に今日の日付と半年前の日付をString型に変換
		 */
		strToday = today.toString();
		strDayOfHalfAYearAgo = dayOfHalfAYearAgo.toString();

		/**
		 * 誕生日と今日の日付からおみくじコードを取得
		 */
		omikujiId = resultService.getOmikujiIdFindByFortunedayAndBirthday(today, sqlBirthday);

		/**
		 * おみくじコードが0の時(同日・同誕生日でおみくじを引いていない場合)
		 * おみくじテーブルの行数から乱数を生成し、おみくじコードとして取得
		 */
		if(omikujiId == 0) {
			Random r  = new Random();
			omikujiId = r.nextInt((int) omikujiService.getCount());
		}

		/**
		 * 取得したおみくじコードからおみくじの結果を取得
		 */
		omikujiBean = omikujiService.getOmikujiFindByOmikujiId(omikujiId);

		/**
		 * 同日・同誕生日でおみくじを引いていない場合
		 * 今回の結果をresultテーブルに登録
		 */
		if(resultService.getOmikujiIdFindByFortunedayAndBirthday(today, sqlBirthday) == 0) {
			result = new Result();
			result.fortuneDay = today;
			result.birthday = sqlBirthday;
			result.omikujiId = omikujiId;
			result.createDay = today;
			result.creater = "小田健太郎";
			resultService.insert(result);
		}

		return "/problem6/omikuji.jsp";
	}

}
