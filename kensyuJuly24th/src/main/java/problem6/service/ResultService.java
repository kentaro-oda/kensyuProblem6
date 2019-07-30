package problem6.service;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import problem6.dto.GetFortuneCountDto;
import problem6.dto.GetFortuneCountDuringTodayParam;
import problem6.dto.GetFortuneCountForHalfAYearParam;
import problem6.dto.OmikujiBean;
import problem6.entity.Result;

/**
 * 結果テーブルに関するDB処理のサービスクラス
 * AbstractServiceを継承
 *
 * @author k_oda
 *
 */
public class ResultService extends AbstractService<Result> {

	/**
	 * 各運勢の件数を求めるループカウンタに使うためFortuneServiceをDI
	 */
	@Resource
	protected FortuneService fortuneService;

	/**
	 * 当日の日付と入力誕生日からおみくじコードを取得するメソッド
	 * @param today		今日の日付
	 * @param birthday	入力された誕生日
	 * @return resultBean.getOmikujiId()/取得したおみくじコード(なかった場合は０を返す)
	 */
	public int getOmikujiIdFindByFortunedayAndBirthday(Date today, Date birthday) {
		/**
		 * 今日の日付と誕生日からおみくじコードを検索
		 */
		Result result = jdbcManager.from(Result.class).eager("omikujiId")
				.id(today, birthday).getSingleResult();
		/**
		 * おみくじコードを取得するためのdtoの変数作成
		 */
		OmikujiBean omikujiBean = new OmikujiBean();

		/**
		 * 検索結果がnullだったら(=同日・同誕生日でおみくじを引いていなければ)
		 */
		if(result == null) {
			/**
			 * レコードがなかった場合にresultBeanに０を登録
			 */
			omikujiBean.setOmikujiId(0);
		}else {
			/**
			 * レコードが存在すれば取得したおみくじコードを登録
			 */
			omikujiBean.setOmikujiId(result.omikujiId);
		}

		return omikujiBean.getOmikujiId();
	}

	/**
	 * 過去半年間の全結果の行数を返すメソッド
	 *
	 * @param dayOfHalfAYearAgo	半年前の日付
	 * @param today				今日の日付
	 * @return count/ヒットした行数
	 */
	public double getAllCountForHalfAYear(Date dayOfHalfAYearAgo, Date today) {
		/**
		 * 過去半年間の全結果数を検索
		 */
		double count = jdbcManager.from(Result.class).where("fortune_day >= ? AND fortune_day <= ?",
				dayOfHalfAYearAgo, today).getCount();

		return count;
	}

	/**
	 * 過去半年間の各運勢の行数を返すメソッド
	 *
	 * @param dayOfHalfAYearAgo	半年前の日付
	 * @param today				今日の日付
	 * @return fortuneCountMap/各運勢の名前と行数が紐づいて登録されたマップ
	 */
	public Map <String, Long> getFortuneCountForHalfAYear(Date dayOfHalfAYearAgo, Date today){

		/**
		 * 運勢名と件数を登録するマップをLinkedHashMap型で作成
		 */
		Map<String, Long> fortuneCountMap = new LinkedHashMap<>();

		/**
		 * パラメータクラス型の変数を作成し、引数の値を格納
		 */
		GetFortuneCountForHalfAYearParam param = new GetFortuneCountForHalfAYearParam();
		param.dayOfHalfAYearAgo = dayOfHalfAYearAgo;
		param.today = today;

		/**
		 * 運勢テーブルの行数分for文を回す
		 */
		for(int i = 1; i <= fortuneService.getCount(); i++) {
			/**
			 * ループの回数をパラメータのfortuneIdとして登録
			 */
			param.fortuneId = i;

			/**
			 * 外部SQLファイルを用いて過去半年間の運勢ごとの件数を取得
			 */
			GetFortuneCountDto result = jdbcManager.selectBySqlFile(GetFortuneCountDto.class,
					"problem6/service/ResultService_getFortuneCountForHalfAYear.sql", param).getSingleResult();

			/**
			 * 件数がなかった場合、0を登録する
			 */
			if(result.count == null) {
				result.count = 0L;
			}

			/**
			 * キーに運勢名、バリューに件数を登録
			 */
			fortuneCountMap.put(result.fortuneName, result.count);
		}

		return fortuneCountMap;
	}

	/**
	 * 今日１日の全結果の行数を返すメソッド
	 *
	 * @param today	今日の日付
	 * @return count/ヒットした行数
	 */
	public double getAllCountDurringToday(Date today) {

		/**
		 * 今日1日の全結果数を検索
		 */
		double count = jdbcManager.from(Result.class).where("fortune_day = ?", today).getCount();

		return count;
	}

	/**
	 * 今日１日の各運勢の行数を返すメソッド
	 *
	 * @param today	今日の日付
	 * @return	fortuneCountMap/各運勢の名前と行数が紐づいて登録されたマップ
	 */
	public Map <String, Long> getFortuneCountDuringToday(Date today){
		/**
		 * 運勢名と件数を登録するマップをLinkedHashMap型で作成
		 */
		Map<String, Long> fortuneCountMap = new LinkedHashMap<>();

		/**
		 * パラメータクラス型の変数を作成し、引数の値を格納
		 */
		GetFortuneCountDuringTodayParam param = new GetFortuneCountDuringTodayParam();
		param.today = today;

		/**
		 * 運勢テーブルの行数分for文を回す
		 */
		for(int i = 1; i <= fortuneService.getCount(); i++) {

			/**
			 * ループの回数をパラメータのfortuneIdとして登録
			 */
			param.fortuneId = i;

			/**
			 * 外部SQLファイルを用いて今日一日の運勢ごとの件数を取得
			 */
			GetFortuneCountDto result = jdbcManager.selectBySqlFile(GetFortuneCountDto.class,
					"problem6/service/ResultService_getFortuneCountDuringToday.sql", param).getSingleResult();

			/**
			 * 件数がなかった場合、0を登録する
			 */
			if(result.count == null) {
				result.count = 0L;
			}

			/**
			 * キーに運勢名、バリューに件数を登録
			 */
			fortuneCountMap.put(result.fortuneName, result.count);
		}

		return fortuneCountMap;
	}
}
