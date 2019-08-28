package problem6.dto;

/**
 * 住所テーブルのDTO
 * 各フィールドはsetter/getterメソッドを持つ
 *
 * @author k_oda
 *
 */
public class PostalBean {

	//ID
	private int id;

	//郵便番号
	private String postalCode;

	//都道府県名
	private String prefecture;

	//市区町村名
	private String city;

	//番地以降
	private String addressDetail;

	//検索用市区町村名
	private String cityEscaper;

	//検索用番地以降
	private String addressDetailEscaper;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getCityEscaper() {
		return cityEscaper;
	}

	public void setCityEscaper(String cityEscaper) {
		this.cityEscaper = cityEscaper;
	}

	public String getAddressDetailEscaper() {
		return addressDetailEscaper;
	}

	public void setAddressDetailEscaper(String addressDetailEscaper) {
		this.addressDetailEscaper = addressDetailEscaper;
	}

}
