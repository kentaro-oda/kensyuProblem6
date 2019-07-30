package problem6.service;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import problem6.dto.FortuneBean;
import problem6.dto.GetResultForHalfAYearFindByBirthdayDto;
import problem6.dto.GetResultForHalfAYearFindByBirthdayParam;
import problem6.dto.OmikujiBean;
import problem6.entity.Omikuji;

/**
 * おみくじテーブルに関するDB処理のサービスクラス
 * AbstractServiceを継承
 * 継承しているクラスの全権の行数を取得するメソッドも使う(public long getCount())
 *
 * @author k_oda
 *
 */
public class OmikujiService extends AbstractService<Omikuji> {

	/**
	 * 運勢名を獲得するメソッドを使用するためFortuneService型変数をDI
	 */
	@Resource
	protected FortuneService fortuneService;

	/**
	 * おみくじコードから運勢名、願い事、商い、学問を取得するメソッド
	 *
	 * @param omikujiId	おみくじコード
	 * @return	omikujiBean	検索した情報を格納したOmikujiBean型変数
	 */
	public OmikujiBean getOmikujiFindByOmikujiId(int omikujiId) {

		/**
		 * おみくじコードから運勢コード、願い事、商い、学問を検索し、エンティティ型変数に格納
		 */
		Omikuji omikuji = jdbcManager.from(Omikuji.class).eager("fortuneId", "negaigoto", "akinai", "gakumon")
				.id(omikujiId).getSingleResult();

		/**
		 * 検索した運勢コードからさらに運勢名を検索しFortuneBean型変数に格納
		 */
		FortuneBean fortune = fortuneService.getFortuneNameFindByFortuneId(omikuji.fortuneId);

		/**
		 * OmikujiBean型変数を作成し、必要事項を登録し返却
		 */
		OmikujiBean omikujiBean = new OmikujiBean();
		omikujiBean.setFortune(fortune);
		omikujiBean.setNegaigoto(omikuji.negaigoto);
		omikujiBean.setAkinai(omikuji.akinai);
		omikujiBean.setGakumon(omikuji.gakumon);

		return omikujiBean;
	}

	/**
	 * 過去半年間の入力誕生日の結果を取得するメソッド
	 *
	 * @param sqlBirthday		誕生日
	 * @param dayOfHalfAYearAgo	半年前の日付
	 * @param today				今日の日付
	 * @return	resultForHalfAYearMap	過去半年間の入力誕生日の結果が格納されたマップ
	 */
	public Map<Date, OmikujiBean> getresultForHalfAYear(Date sqlBirthday, Date dayOfHalfAYearAgo, Date today){

		/**
		 * パラメータクラス型の変数を作成し、引数から値を格納
		 */
		GetResultForHalfAYearFindByBirthdayParam param = new GetResultForHalfAYearFindByBirthdayParam();
		param.sqlBirthday = sqlBirthday;
		param.dayOfHalfAYearAgo = dayOfHalfAYearAgo;
		param.today = today;

		/**
		 * 外部SQLファイルを用いて過去半年間の入力誕生日の結果を全て取得しDTO型リストに格納
		 */
		List<GetResultForHalfAYearFindByBirthdayDto> results = jdbcManager.selectBySqlFile
				(GetResultForHalfAYearFindByBirthdayDto.class, "problem6/service/OmikujiService_getResultForHalfAYearFindByBirthday.sql", param).getResultList();

		/**
		 * キーに占い日、バリューに結果をもつMap型変数をLinkedHashMap型で作成
		 */
		Map<Date, OmikujiBean> resultForHalfAYearMap = new LinkedHashMap<>();

		/**
		 * 検索した結果をマップに登録するために拡張for文を使用
		 */
		for(GetResultForHalfAYearFindByBirthdayDto result : results) {

			/**
			 * FortuneBean型の変数を作成し運勢名をセット
			 */
			FortuneBean fortuneBean = new FortuneBean();
			fortuneBean.setFortuneName(result.fortuneName);

			/**
			 * OmikujiBean型の変数を作成し、運勢、願い事、商い、学問をそれぞれセット
			 */
			OmikujiBean omikujiBean = new OmikujiBean();
			omikujiBean.setFortune(fortuneBean);
			omikujiBean.setNegaigoto(result.negaigoto);
			omikujiBean.setAkinai(result.akinai);
			omikujiBean.setGakumon(result.gakumon);

			/**
			 * キーに検索時に取得した占い日、バリューに値をセットしたomikujiBeanを登録
			 */
			resultForHalfAYearMap.put(result.fortuneDay, omikujiBean);
		}

		return resultForHalfAYearMap;
	}
}
