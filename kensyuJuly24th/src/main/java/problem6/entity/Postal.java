package problem6.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 住所テーブルのエンティティ
 *
 * @author k_oda
 *
 */
@Entity
public class Postal {

	//登録順ID
	@Id
	public int id;

	//郵便番号
	public String postalCode;

	//都道府県名
	public String prefecture;

	//市区町村名
	public String city;

	//番地以降の住所
	public String addressDetail;

	//検索用の市区町村名
	public String cityEscaper;

	//検索用の番地以降名
	public String addressDetailEscaper;
}
