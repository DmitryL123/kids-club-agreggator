package ru.dmilut.prodlenka.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.dmilut.prodlenka.entity.User;
import ru.dmilut.prodlenka.service.UnitService;
import ru.dmilut.prodlenka.service.UserService;

@Controller
public class UnitController {

	@Autowired
	private UnitService unitService;

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User constructUser() {

		return new User();
	}

	@RequestMapping("/units")
	public String units(Model model) {
		model.addAttribute("units", unitService.findAll());

		return "units";
	}

	@RequestMapping("/unit/remove/{id}")
	public String removeUnit(@PathVariable long id, Principal principal) {
		String name = principal.getName();
		if (name.equals("admin")) {
			unitService.delete(id);
		}

		return "redirect:/units.html";
	}

}
