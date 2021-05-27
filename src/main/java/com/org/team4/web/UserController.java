package com.org.team4.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.team4.dto.LoginDTO;
import com.org.team4.dto.UserDTO;
import com.org.team4.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "users/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session) {

		log.info(loginDTO.toString());

		try {
			UserDTO userInfo = userService.getUser(loginDTO);

			session.setAttribute("userInfo", userInfo);

			return "redirect:../";

		} catch (Exception e) {
			log.info(e.getMessage());
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "./login");
			return "result";
		}
	}

	@GetMapping("/logout")
	public String logout(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session) {

		log.info(loginDTO.toString());

		UserDTO userInfo = (UserDTO) session.getAttribute("userInfo");
		session.invalidate();

		return "redirect:../";
	}

	@GetMapping("/register")
	public String register(HttpSession session) {

		UserDTO userInfo = (UserDTO) session.getAttribute("userInfo");

		if (userInfo != null)
			return "redirect:../users/login";
		else
			return "users/register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute UserDTO userDTO, Model model, HttpSession session) {

		try {
			userService.registerUser(userDTO);
			return "redirect:../";

		} catch (Exception e) {
			log.info(e.getMessage());
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "./");
			return "result";
		}

	}

	@GetMapping("/withdraw")
	public String withdraw(HttpSession session) {

		UserDTO userInfo = (UserDTO) session.getAttribute("userInfo");

		if (userInfo == null)
			return "redirect:../users/login";
		else
			return "users/withdraw";
	}

	@PostMapping("/withdraw")
	public String withdraw(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session) {

		try {
			userService.withdrawUser(loginDTO);
			session.invalidate();
			return "redirect:../";

		} catch (Exception e) {
			log.info(e.getMessage());
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "./");
			return "result";
		}
	}
}