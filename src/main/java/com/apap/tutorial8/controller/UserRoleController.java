package com.apap.tutorial8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.security.UserDetailsServiceImpl;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@Autowired
	private UserDetailsServiceImpl userDetail;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value = "/updatePassword")
	private String updatePassword() {
		return "update-password";
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	private String updatePasswordSubmit(Model model,
			@RequestParam(value = "passwordLama") String passwordLama, 
			@RequestParam(value = "passwordBaru") String passwordBaru,
			@RequestParam(value = "konfirmasi") String konfrimasi) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails user = userDetail.loadUserByUsername(currentUserName);
		if (passwordEncoder.matches(passwordLama, user.getPassword())) {
			userService.updatePassword(currentUserName, passwordBaru);
		} else {
			model.addAttribute("message", "Password lama salah!" );
			return "update-password";
		}
		return "update";
	}
	
}
