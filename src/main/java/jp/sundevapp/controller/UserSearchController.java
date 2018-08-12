package jp.sundevapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.sundevapp.dto.UserDto;
import jp.sundevapp.form.UserSearchForm;
import jp.sundevapp.service.UserService;

@Controller
public class UserSearchController {

	private final String  PAGE = "page/userSearch";

	@Autowired
	private UserService service;

	@ModelAttribute("form")
	private UserSearchForm initForm() {
		return new UserSearchForm();
	}

	@RequestMapping("/userSearch")
	public String init(@ModelAttribute("form") UserSearchForm form, Model model) {
		form.setUserId("666");

		List<UserDto> list = service.search(new UserDto());
//		UserDto user1 = new UserDto();
//		user1.setUserId("111");
//		user1.setName("すなふきん");
//		form.getSearchList().add(user1);
//		UserDto user2 = new UserDto();
//		user2.setUserId("222");
//		user2.setName("むーみん");
		form.setSearchList(list);

		model.addAttribute("form", form);
		return PAGE;
	}
}
