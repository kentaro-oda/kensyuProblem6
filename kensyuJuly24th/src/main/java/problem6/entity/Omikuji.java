package problem6.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * おみくじテーブルのエンティティ
 * Commonエンティティを継承
 *
 * @author k_oda
 *
 */
@Entity
public class Omikuji extends Common {

	/**
	 * おみくじコード(主キー)
	 */
	@Id
	public int omikujiId;

	/**
	 * 運勢コード
	 */
	@Basic(fetch = FetchType.LAZY)
	public int fortuneId;

	/**
	 * 願い事
	 */
	@Basic(fetch = FetchType.LAZY)
	public String negaigoto;

	/**
	 * 商い
	 */
	@Basic(fetch = FetchType.LAZY)
	public String akinai;

	/**
	 * 学問
	 */
	@Basic(fetch = FetchType.LAZY)
	public String gakumon;

	/**
	 * 結果エンティティとの結合
	 */
	@OneToMany(mappedBy = "omikuji")
	public List<Result> resultList;

	/**
	 * 運勢エンティティとの結合
	 */
	@ManyToOne
	@JoinColumn(name = "FORTUNE_ID", referencedColumnName = "FORTUNE_ID")
	public Fortune fortune;
}