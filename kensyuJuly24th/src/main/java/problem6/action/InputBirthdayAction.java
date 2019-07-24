package problem6.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import problem6.form.BirthdayForm;

public class InputBirthdayAction {
	
	@Resource
	@ActionForm
	protected BirthdayForm birthdayForm;
	
	@Execute(validator = false)
	public String index() {
		return "inputBirthday.jsp";
	}
}
