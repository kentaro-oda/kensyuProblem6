package problem6.dto;

import java.sql.Date;

/**
 * 各テーブルに共通するカラムのDTO抽象クラス
 * 各フィールドはsetter/getterメソッドを持つ
 *
 * @author k_oda
 *
 */

public abstract class CommonBean {

	/**
	 * 更新者
	 */
	private String updater;

	/**
	 * 更新日
	 */
	private Date updateDay;

	/**
	 * 作成者
	 */
	private String creater;

	/**
	 * 作成日
	 */
	private Date createDay;

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(Date updateDay) {
		this.updateDay = updateDay;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}
}
