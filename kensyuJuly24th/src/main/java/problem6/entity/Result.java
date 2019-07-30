package problem6.entity;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 結果テーブルのエンティティ
 * Commonエンティティを継承
 *
 * @author k_oda
 *
 */
@Entity
public class Result extends Common {

	/**
	 * 占い日(主キー)
	 */
	@Id
	public Date fortuneDay;

	/**
	 * 誕生日(主キー)
	 */
	@Id
	public Date birthday;

	/**
	 * おみくじコード
	 */
	@Basic(fetch = FetchType.LAZY)
	public int omikujiId;

	/**
	 * おみくじエンティティとの結合
	 */
	@ManyToOne
	@JoinColumn(name = "OMIKUJI_ID", referencedColumnName = "OMIKUJI_ID")
	public Omikuji omikuji;
}
