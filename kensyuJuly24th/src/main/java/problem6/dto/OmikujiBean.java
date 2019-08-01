package problem6.dto;

/**
 * おみくじテーブルのDTO
 * 各フィールドはsetter/getterメソッドを持つ
 * CommonBeanを継承
 *
 * @author k_oda
 *
 */
public class OmikujiBean extends CommonBean {

	//おみくじコード
	private int omikujiId;

	//運勢(FortuneBeanクラスの要素持つ)
	private FortuneBean fortune;

	//願い事
	private String negaigoto;

	//商い
	private String akinai;

	//学問
	private String gakumon;

	public int getOmikujiId() {
		return omikujiId;
	}

	public void setOmikujiId(int omikujiId) {
		this.omikujiId = omikujiId;
	}

	public FortuneBean getFortune() {
		return fortune;
	}

	public void setFortune(FortuneBean fortune) {
		this.fortune = fortune;
	}

	public String getNegaigoto() {
		return negaigoto;
	}

	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}

	public String getAkinai() {
		return akinai;
	}

	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}

	public String getGakumon() {
		return gakumon;
	}

	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}


}
