
package usecases;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Department;
import domain.Employee;
import services.DepartmentService;
import services.EmployeeService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentTest extends AbstractTest {

	@Autowired
	private DepartmentService	_departmentService;

	@Autowired
	private EmployeeService		_employeeService;


	@Autowired
	//	private DepartmentAdministratorController _departmentController;

	// Tests -----------------------------------------------------------

	private Employee createEmployee() {
		final Employee e = this._employeeService.create();

		return e;
	}

	private Employee saveEmployee(Employee e) {
		Employee saved;

		e = this._employeeService.create();
		e.setName("test employee 1");

		saved = this._employeeService.save(e);

		return saved;
	}

	private Department createDepartment() {
		final Department d = this._departmentService.create();

		return d;
	}

	private Department saveDepartment(Department d) {
		Department saved;

		d = this._departmentService.create();
		d.setName("test department 1");
		d.setBuilding("test building 1");

		saved = this._departmentService.save(d);

		return saved;
	}

	@Test
	@Transactional
	public void testSaveDeparment() {
		Department d, saved;
		Collection<Department> d_list;

		d = this._departmentService.create();
		d.setName("test department 1");
		d.setBuilding("test department building 1");

		saved = this._departmentService.save(d);

		d_list = this._departmentService.findAll();

		Assert.isTrue(d_list.contains(saved));
	}

	@Test
	@Transactional
	public void testFindAllDepartments() {
		Department d, saved;
		Collection<Department> d_list;

		d = this._departmentService.create();
		d.setName("test department 1");
		d.setBuilding("test department building 1");

		saved = this._departmentService.save(d);

		d_list = this._departmentService.findAll();

		Assert.isTrue(d_list != null, "la lista de departamentos es nula");
		Assert.isTrue(!d_list.isEmpty(), "La lista de departamentos está vacía");
	}

	@Test
	@Transactional
	public void testAddEmployeeToDeparment() {
		Department d;
		final Department saved;
		Employee e;
		final Employee e_saved;

		final Collection<Department> d_list;
		final Collection<Employee> e_list;

		d = this.createDepartment();
		d = this.saveDepartment(d);

		e = this.createEmployee();
		e = this.saveEmployee(e);
		Assert.notNull(e);
		Assert.isTrue(e.getId() != 0);

		this._employeeService.assignDepartment(e, d);

		final Department res = this._departmentService.findOne(d.getId());

		e_list = res.getEmployees();

		Assert.isTrue(e_list.contains(e), "El empleado no está en el departamento");
	}

	@Test
	@Transactional
	public void testAddEmployeeListToDeparment() {
		Department d;
		final Department saved;
		Employee e;
		final Employee e_saved;

		final Collection<Department> d_list;
		final Collection<Employee> e_list = new HashSet<Employee>();

		d = this.createDepartment();
		d = this.saveDepartment(d);

		e = this.createEmployee();
		e = this.saveEmployee(e);
		Assert.notNull(e);
		Assert.isTrue(e.getId() != 0);

		e_list.add(e);

		e = this.createEmployee();
		e = this.saveEmployee(e);
		Assert.notNull(e);
		Assert.isTrue(e.getId() != 0);

		e_list.add(e);

		e = this.createEmployee();
		e = this.saveEmployee(e);
		Assert.notNull(e);
		Assert.isTrue(e.getId() != 0);

		e_list.add(e);

		this._departmentService.addEmployeesToDepartment(d, e_list);

		final Department res = this._departmentService.findOne(d.getId());

		Assert.isTrue(e_list.size() == 3);
		Assert.isTrue(res.getEmployees().size() == 3);

		Assert.isTrue(res.getEmployees().containsAll(e_list), "Las listas de empleados no casan");
	}

	@Test
	@Transactional
	public void testRemoveEmployeeToDeparment() {
		Department d;
		final Department saved;
		Employee e;
		final Employee e_saved;

		final Collection<Department> d_list;
		final Collection<Employee> e_list;

		d = this.createDepartment();
		d = this.saveDepartment(d);

		e = this.createEmployee();
		e = this.saveEmployee(e);
		Assert.notNull(e);
		Assert.isTrue(e.getId() != 0);

		this._employeeService.assignDepartment(e, d);

		Department res = this._departmentService.findOne(d.getId());

		e_list = res.getEmployees();

		Assert.isTrue(e_list.contains(e), "El empleado no está en el departamento");

		this._employeeService.removeEmployeeFromDepartment(e);

		res = this._departmentService.findOne(d.getId());

		Assert.isTrue(res.getEmployees().contains(e) == false);
	}

	//	@Test
	//	public void createDepartmentAdministratorController() {
	//		this._departmentController.
	//	}

}
