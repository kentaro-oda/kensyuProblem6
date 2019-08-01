package problem6.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import problem6.form.DaysForm;
import problem6.service.ResultService;

/**
 * 過去半年間と今日一日の各運勢の割合を計算し取得するアクションクラス
 *
 * @author k_oda
 *
 */
public class GetRateAction {

	//結果画面から送られてくるアクションフォーム DaysFormをDI
	@Resource
	@ActionForm
	public DaysForm daysForm;

	//割合を求めるために必要な数を求めるためResultServiceをDI
	@Resource
	public ResultService resultService;

	/*
	 * メソッド内で使われるフィールド
	 *
	 * dayOfHalfAYearAgo	半年前の日付
	 * today		今日の日付
	 * count		半年間or今日一日のおみくじの全件数
	 * fortuneCountMap	キーに運勢名、バリューにその件数を格納したマップ
	 * fortuneRateForHalfAYearMap	キーに運勢名、バリューにその運勢の半年間の割合を格納したマップ
	 * rate		割合
	 * bigDecimal	割合を変換したBigDecimal型変数
	 * bdRate	四捨五入をした割合
	 * fortuneRateDuringTodayMap	キーに運勢名、バリューにその運勢の今日一日の割合を格納したマップ
	 */
	public Date dayOfHalfAYearAgo;
	public Date today;
	public double count;
	public Map<String, Long> fortuneCountMap;
	public Map<String, BigDecimal> fortuneRateForHalfAYearMap;
	public double rate;
	public BigDecimal bigDecimal;
	public BigDecimal bdRate;
	public Map<String, BigDecimal> fortuneRateDuringTodayMap;

	/**
	 * 過去半年間と今日一日の各運勢の割合を計算し取得するメソッド
	 *
	 * @return	/problem6/rate.jsp	割合を表示させる画面
	 */
	@Execute(validator = false)
	public String getRate() {

		//フォームから受け取った情報をDate型に変換
		dayOfHalfAYearAgo = Date.valueOf(daysForm.strDayOfHalfAYearAgo);
		today = Date.valueOf(daysForm.strToday);

		//過去半年間の各運勢の割合を格納するマップをLinkedHashMap型で作成
		fortuneRateForHalfAYearMap = new LinkedHashMap<>();

		//過去半年間の全件数と運勢ごとの件数を取得
		count = resultService.getAllCountForHalfAYear(dayOfHalfAYearAgo, today);
		fortuneCountMap = resultService.getFortuneCountForHalfAYear(dayOfHalfAYearAgo, today);

		//運勢ごとに割合を計算し、四捨五入したものをマップに登録
		for(String fortuneName : fortuneCountMap.keySet()) {
			rate = (fortuneCountMap.get(fortuneName) / count) * 100;
			bigDecimal = new BigDecimal(rate);
			bdRate = bigDecimal.setScale(1, RoundingMode.HALF_UP);
			fortuneRateForHalfAYearMap.put(fortuneName, bdRate);
		}

		//各運勢の件数を格納するマップを空にする
		fortuneCountMap.clear();

		//今日一日の各運勢の割合を格納するマップをLinkedHashMap型で作成
		fortuneRateDuringTodayMap = new LinkedHashMap<>();

		//今日一日の全件数と運勢ごとの件数を取得
		count = resultService.getAllCountDurringToday(today);
		fortuneCountMap = resultService.getFortuneCountDuringToday(today);

		//運勢ごとに割合を計算し、四捨五入したものをマップに登録
		for(String fortuneName : fortuneCountMap.keySet()) {
			rate = (fortuneCountMap.get(fortuneName) / count) * 100;
			bigDecimal = new BigDecimal(rate);
			bdRate = bigDecimal.setScale(1, RoundingMode.HALF_UP);
			fortuneRateDuringTodayMap.put(fortuneName, bdRate);
		}

		//各運勢の件数を格納するマップを空にする
		fortuneCountMap.clear();


		return "/problem6/rate.jsp";
	}

}
