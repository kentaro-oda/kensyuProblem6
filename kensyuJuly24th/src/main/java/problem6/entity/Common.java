package problem6.entity;

import java.sql.Date;

import javax.persistence.MappedSuperclass;

/**
 * 各テーブルに共通するカラムの抽象クラスエンティティ
 * @author k_oda
 *
 */
@MappedSuperclass
public abstract class Common {

	/**
	 * 更新者
	 */
	public String updater;

	/**
	 * 更新日
	 */
	public Date updateDay;

	/**
	 * 作成者
	 */
	public String creater;

	/**
	 * 作成日
	 */
	public Date createDay;
}
