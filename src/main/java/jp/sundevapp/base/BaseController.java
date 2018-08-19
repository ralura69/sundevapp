package jp.sundevapp.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	@Autowired
	private LoginUserInfo loginUserInfo;

	protected LoginUserInfo getLoginUserInfo() {
		return this.loginUserInfo;
	}
}
