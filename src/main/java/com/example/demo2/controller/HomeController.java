package com.example.demo2.controller;

import com.example.demo2.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
	
	private static List<Person> persons = new ArrayList<Person>();
	
	static {
		persons.add(new Person("Tuan", "Le"));
		persons.add(new Person("Le", "Tuan"));
	}
	
	@RequestMapping("/")
	public String welcome(final Model model) {
		model.addAttribute("message", "hello");
		return "index";
	}
	
	@RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
	public String personList(Model model) {
		model.addAttribute("persons", persons);
		return "personList";
	}
	
	@RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {
		Person personForm = new Person();
		model.addAttribute("personForm", personForm);
		return "addPerson";
	}
	
	@RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
	public String savePerson(Model model, @ModelAttribute("personForm") Person personForm) {
		
		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();
		
		if (firstName != null && firstName.length() > 0 && lastName != null & lastName.length() > 0) {
			persons.add(personForm);
			return "redirect:/personList";
		}
		
		model.addAttribute("errorMessage", "FirstName or LastName is required");
		return "addPerson";
	}
	
	@RequestMapping("/hello/{param}")
	@ResponseBody
	public String hello(@PathVariable("param") String param) {
		return "Hello " + param;
	}
}
