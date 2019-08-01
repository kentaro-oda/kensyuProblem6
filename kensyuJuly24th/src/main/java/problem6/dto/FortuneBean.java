package problem6.dto;

/**
 * 運勢テーブルのDTO
 * 各フィールドはsetter/getterメソッドを持つ
 * CommonBeanを継承
 *
 * @author k_oda
 *
 */
public class FortuneBean extends CommonBean{

	//運勢コード
	private int fortuneId;

	//運勢名
	private String fortuneName;

	public int getFortuneId() {
		return fortuneId;
	}

	public void setFortuneId(int fortuneId) {
		this.fortuneId = fortuneId;
	}

	public String getFortuneName() {
		return fortuneName;
	}

	public void setFortuneName(String fortuneName) {
		this.fortuneName = fortuneName;
	}


}
