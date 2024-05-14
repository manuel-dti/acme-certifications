/*
 * WelcomeController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Department;
import services.DepartmentService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	private DepartmentService departmentService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("moment", moment);

		return result;
	}

	// --------------------------------------------------------------

	@RequestMapping(value = "/department")
	public ModelAndView listDepartments(@RequestParam(required = false, defaultValue = "John Doe") final String name) {
		ModelAndView result;
		Collection<Department> departments;

		departments = this.departmentService.findAll();
		result = new ModelAndView("welcome/department");
		//		result.addObject("requestURI", "department/administrator/list.do");
		//		result.addObject("departments", departments);

		return result;
	}

}
