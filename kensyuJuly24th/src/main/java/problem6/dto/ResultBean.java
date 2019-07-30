package problem6.dto;

import java.sql.Date;

/**
 * 結果テーブルのDTO
 * 各フィールドはsetter/getterメソッドを持つ
 * CommonBeanを継承
 *
 * @author k_oda
 *
 */
public class ResultBean extends CommonBean {

	/**
	 * 占い日
	 */
	private Date fortuneDay;

	/**
	 * 入力した誕生日
	 */
	private Date birthday;

	/**
	 * おみくじ(OmikujiBeanの要素もつ)
	 */
	private OmikujiBean omikuji;

	public Date getFortuneDay() {
		return fortuneDay;
	}

	public void setFortuneDay(Date fortuneDay) {
		this.fortuneDay = fortuneDay;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public OmikujiBean getOmikuji() {
		return omikuji;
	}

	public void setOmikuji(OmikujiBean omikuji) {
		this.omikuji = omikuji;
	}

}
