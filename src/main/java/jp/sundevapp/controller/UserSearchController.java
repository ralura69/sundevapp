package jp.sundevapp.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.sundevapp.dto.UserDto;
import jp.sundevapp.form.UserSearchForm;
import jp.sundevapp.service.UserService;

@Controller
public class UserSearchController {

	@Autowired
	private UserService service;

	private final String  PAGE = "page/userSearch";

	@ModelAttribute("form")
	private UserSearchForm initForm() {
		return new UserSearchForm();
	}


	@RequestMapping(path = "/userSearch", method = RequestMethod.GET)
	public String init() {
		return PAGE;
	}

	@RequestMapping(value = "/userSearch", method = RequestMethod.POST)
	public String search(@ModelAttribute("form") UserSearchForm form, Model model) {

		UserDto paramDto = new UserDto();
		BeanUtils.copyProperties(form, paramDto);

		List<UserDto> list = service.search(paramDto);

		form.setSearchList(list);
		model.addAttribute("form", form);
		return PAGE;
	}


}
