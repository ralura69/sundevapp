package jp.sundevapp.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.sundevapp.base.BaseController;
import jp.sundevapp.dto.UserDto;
import jp.sundevapp.form.LoginForm;
import jp.sundevapp.service.LoginService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	private LoginService service;
	@Autowired
	protected MessageSource msg;

	private final String  PAGE = "page/login";

	@ModelAttribute("form")
	private LoginForm initForm() {
		return new LoginForm();
	}


	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String init(@ModelAttribute("form") LoginForm form, Model model) {
		model.addAttribute("form", form);
		return PAGE;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {

		if (result.hasErrors())
			return PAGE;

		UserDto resultDto = service.isAuth(form.getUserId(), form.getPassword());
		if (resultDto == null) {
			result.rejectValue("password", null, msg.getMessage("custom.validation.loginInvalid", null, Locale.JAPAN));
			return PAGE;
		}

		getLoginUserInfo().setLoginUserInfo(resultDto);
		return "redirect:/userSearch";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		UserDto delDto = new UserDto();
		getLoginUserInfo().setLoginUserInfo(delDto);
		return "redirect:/login";
	}
}
