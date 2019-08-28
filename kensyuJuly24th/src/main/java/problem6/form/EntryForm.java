package problem6.form;

import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

/**
 * エントリー時のアクションフォーム
 * @author k_oda
 *
 */
public class EntryForm {

	//名前(必須入力)
	@Required(msg = @Msg(key = "errors.nameRequired"))
	public String name;

	//郵便番号(7桁チェック)
	@Minlength(minlength = 7, msg = @Msg(key = "errors.postalCode"))
	@Maxlength(maxlength = 7, msg = @Msg(key = "errors.postalCode"))
	public String postalCode;

	//都道府県名
	public String prefecture;

	//市区町村名
	public String city;

	//番地以降の住所
	public String addressDetail;

	//電話番号(10桁か11桁チェック)
	@Minlength(minlength = 10, msg = @Msg(key = "errors.phoneNumber"))
	@Maxlength(maxlength = 11, msg = @Msg(key = "errors.phoneNumber"))
	public String phoneNumber;

	//メールアドレス(メアド形式チェック)
	@Required(msg = @Msg(key = "errors.mailRequired"))
	@EmailType
	public String mail;

	//メールで送られるString型占い日(hiddenでもらってくる)
	public String strToday;

	//結果画面に戻る時に必要、かつメールで送られるString型誕生日(hiddenでもらってくる)
	public String birthday;

	//メールで送られる運勢名(hiddenでもらってくる)
	public String fortuneName;

	//メールで送られる願い事(hiddenでもらってくる)
	public String negaigoto;

	//メールで送られる商い(hiddenでもらってくる)
	public String akinai;

	//メールで送られる学問(hiddenでもらってくる)
	public String gakumon;

	//上記のprefecture、city、addressを連結させた完全版の住所
	public String addressAll;
}
