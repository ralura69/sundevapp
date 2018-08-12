package jp.sundevapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class SundevappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SundevappApplication.class, args);
	}

	@RequestMapping("/")
	public String get(Model model) {
		model.addAttribute("title", "菊池　あさひ");
		return "index";
	}
}
