package problem6.service;

import static problem6.entity.FortuneNames.*;

import problem6.dto.FortuneBean;
import problem6.entity.Fortune;

/**
 * 運勢テーブルに関連するDB処理のサービスクラス
 * AbstractServiceを継承
 * 継承しているクラスの全権の行数を取得するメソッドも使う(public long getCount())
 *
 * @author k_oda
 *
 */
public class FortuneService extends AbstractService<Fortune>{

	/**
	 * 運勢コードから運勢名を取得するメソッド
	 * @param fortuneId	運勢コード
	 * @return	fortuneBean	運勢コードと運勢名が入ったDTO
	 */
	public FortuneBean getFortuneNameFindByFortuneId(int fortuneId) {

		//運勢コードから運勢名を検索し、エンティティ型の変数に格納
		Fortune fortune = jdbcManager.from(Fortune.class).eager(fortuneName())
				.id(fortuneId).getSingleResult();

		//DTO型の変数を作成し、それに運勢コードと運勢名格納して返却
		FortuneBean fortuneBean = new FortuneBean();
		fortuneBean.setFortuneId(fortuneId);
		fortuneBean.setFortuneName(fortune.fortuneName);
		return fortuneBean;
	}

}
