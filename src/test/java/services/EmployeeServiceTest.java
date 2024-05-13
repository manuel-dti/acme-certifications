
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Employee;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest extends AbstractTest {

	@Autowired
	private EmployeeService _employeeService;

	// Methods -------------------------------------------------------


	// Tests -----------------------------------------------------------

	private Employee createEmployee() {
		final Employee e = this._employeeService.create();

		return e;
	}

	private Employee saveEmployee(Employee e) {
		Employee saved;

		e = this._employeeService.create();
		e.setName("test department 1");

		saved = this._employeeService.save(e);

		return saved;
	}

	@Test
	@Transactional
	public void testSaveEmployee() {
		Employee e, saved;
		Collection<Employee> e_list;

		e = this.createEmployee();
		saved = this.saveEmployee(e);

		e_list = this._employeeService.findAll();

		Assert.isTrue(e_list.contains(saved));
	}

	@Test
	@Transactional
	public void testFindAllEmployees() {
		Employee e;
		e = this.createEmployee();
		e = this.saveEmployee(e);

		final Collection<Employee> e_list = this._employeeService.findAll();

		Assert.isTrue(e_list != null);
		Assert.isTrue(!e_list.isEmpty());
	}

}
