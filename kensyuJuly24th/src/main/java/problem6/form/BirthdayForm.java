package problem6.form;

import org.seasar.struts.annotation.DateType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

/**
 * 誕生日のみのアクションフォーム(入力チェックも行う)
 * @author k_oda
 *
 */
public class BirthdayForm {

	//手入力した誕生日(必須項目、正規表現、日付のチェックを行う)
	@Required(msg = @Msg(key = "errors.birthdayRequired"))
	@Mask(mask = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")
	@DateType(datePattern = "yyyy-MM-dd")
	public String birthday;

}
