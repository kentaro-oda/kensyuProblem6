package problem6.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import problem6.form.BirthdayForm;

/**
 * 誕生日入力画面の表示と入力チェックをするアクションクラス
 * @author k_oda
 *
 */
public class InputBirthdayAction {

	/**
	 * 入力した誕生日のアクションフォーム BirthdayFormをDI
	 */
	@Resource
	@ActionForm
	public BirthdayForm birthdayForm;

	/**
	 * 誕生日入力画面に遷移するメソッド
	 *
	 * @return	/problem6/inputBirthday.jsp	誕生日入力画面
	 */
	@Execute(validator = false)
	public String index() {
		return "/problem6/inputBirthday.jsp";
	}

	/**
	 * 入力チェックを行うメソッド(入力失敗時は誕生日入力画面に戻る)
	 *
	 * @return	/getOmikuji/getOmikuji/	おみくじを引くメソッド
	 */
	@Execute(validator = true, input = "/problem6/inputBirthday.jsp")
	public String check() {
		return "/getOmikuji/getOmikuji/";
	}

}
