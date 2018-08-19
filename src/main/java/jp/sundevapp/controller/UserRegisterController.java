package jp.sundevapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.sundevapp.base.BaseController;
import jp.sundevapp.dto.UserDto;
import jp.sundevapp.form.UserRegisterForm;
import jp.sundevapp.service.PrefService;
import jp.sundevapp.service.UserService;

@Controller
public class UserRegisterController extends BaseController{

	@Autowired
	private UserService service;
	@Autowired
	private PrefService prefService;
	@Autowired
	protected MessageSource msg;

	private final String  PAGE = "page/userRegister";

	@ModelAttribute("form")
	private UserRegisterForm initForm() {
		UserRegisterForm form = new UserRegisterForm();
		form.setPrefList(prefService.getPrefList());
		return form;
	}


	@RequestMapping(path = "/userRegister", method = RequestMethod.GET)
	public String init(@ModelAttribute("form") UserRegisterForm form, Model model) {
		UserDto dto = null;
		if (form.isEdit()) {
			dto = service.findOne(form.getUserId());
			if (dto != null)
				copyDtoToForm(dto, form);
		}
		model.addAttribute("form", form);
		return PAGE;
	}

	@RequestMapping(path = "/userRegister", method = RequestMethod.POST)
	public String register(@ModelAttribute("form") @Validated UserRegisterForm form, BindingResult result, Model model, RedirectAttributes redirectAttr) {

		if (result.hasErrors() || customValid(form, result)) {
			model.addAttribute("form", form);
			return PAGE;
		}
		UserDto dto = new UserDto();
		copyFormToDto(form, dto);

		UserDto resultDto = service.register(dto);
		copyDtoToForm(resultDto, form);
		form.setInsFlg(resultDto.getInsFlg() ? "1" : "2");

		redirectAttr.addFlashAttribute("form", form);

		// RegUserId = LoginUser
		if (getLoginUserInfo().getUserId().equals(dto.getUserId()))
			getLoginUserInfo().setLoginUserInfo(resultDto);
		return "redirect:/userRegisterComplete";
	}

	@RequestMapping(path = "/userRegisterComplete", method = RequestMethod.GET)
	public String registerComplete(@ModelAttribute("form") UserRegisterForm form, Model model) {
		if ("0".equals(form.getInsFlg()))
			return "redirect:/userSearch";

		model.addAttribute("form", form);
		model.addAttribute("complete", true);
		model.addAttribute("completeFlg", form.getInsFlg());

		return PAGE;
	}
	@RequestMapping(path = "/userDelete", method = RequestMethod.POST)
	public String delete(@ModelAttribute("form") UserRegisterForm form, RedirectAttributes redirectAttr) {
		service.delete(form.getUserId());
		redirectAttr.addFlashAttribute("deleteComplete", true);
		redirectAttr.addFlashAttribute("deleteUserId", form.getUserId());
		return "redirect:/userSearch";
	}


	public Boolean customValid(UserRegisterForm form, BindingResult result) {
		if (!form.isEdit() || form.isPasswordFlg()) {
			if (StringUtils.isEmpty(form.getNewPassword()))
				result.rejectValue("newPassword", null, msg.getMessage("custom.validation.NotEmpty", null, Locale.JAPAN));
			else if (StringUtils.isEmpty(form.getNewPasswordRe()))
				result.rejectValue("newPasswordRe", null, msg.getMessage("custom.validation.NotEmpty", null, Locale.JAPAN));
			else if (!form.getNewPassword().equals(form.getNewPasswordRe()))
				result.rejectValue("newPassword", null, msg.getMessage("custom.validation.PasswordValid", null, Locale.JAPAN));

		}
		return result.hasErrors();
	}
	public void copyDtoToForm(UserDto dto, UserRegisterForm targetForm) {
		BeanUtils.copyProperties(dto, targetForm);

		String ageStr = Optional.ofNullable(dto.getAge())
							.map(a -> String.valueOf(a))
							.orElse("");
		targetForm.setAge(ageStr);

		String birthStr = Optional.ofNullable(dto.getBirth())
							.map(b -> b.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
							.orElse("");
		targetForm.setBirth(birthStr);
	}
	public void copyFormToDto(UserRegisterForm form, UserDto targetDto) {
		BeanUtils.copyProperties(form, targetDto);
		if (!StringUtils.isEmpty(form.getNewPassword()))
			targetDto.setPassword(form.getNewPassword());
		if (!StringUtils.isEmpty(form.getAge()))
			targetDto.setAge(Integer.parseInt(form.getAge()));
		if (!StringUtils.isEmpty(form.getBirth()))
			targetDto.setBirth(LocalDate.parse(form.getBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}
}
