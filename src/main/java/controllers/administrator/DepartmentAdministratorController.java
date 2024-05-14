
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Department;
import services.DepartmentService;

@Controller
@RequestMapping("/department/administrator")
public class DepartmentAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	DepartmentService departmentService;


	// Constructors -----------------------------------------------------------
	public DepartmentAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Department> departments;

		result = new ModelAndView("department/list");

		departments = this.departmentService.findAll();

		result.addObject("requestURI", "department/administrator/list.do");
		result.addObject("departments", departments);
		return result;
	}

	// Create -----------------------------------------------------------------

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Department department;

		department = this.departmentService.create();
		result = this.createEditModelAndView(department);

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Department department) {
		ModelAndView result;

		result = this.createEditModelAndView(department, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Department department, final String message) {
		ModelAndView result;

		result = new ModelAndView("department/edit");
		result.addObject("department", department);
		result.addObject("message", message);

		return result;
	}

}
