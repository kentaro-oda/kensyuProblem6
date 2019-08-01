package problem6.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 運勢テーブルのエンティティ
 * Commonエンティティを継承
 *
 * @author k_oda
 *
 */
@Entity
public class Fortune extends Common{

	//運勢コード(主キー)
	@Id
	public int fortuneId;

	//運勢名
	public String fortuneName;

	//おみくじエンティティとの結合
	@OneToMany(mappedBy = "fortune")
	public List<Omikuji> omikujiList;

}
