
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Department;
import domain.Employee;
import repositories.DepartmentRepository;
import repositories.EmployeeRepository;

@Service
@Transactional
public class DepartmentService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private DepartmentRepository	departmentRepository;

	@Autowired
	private EmployeeRepository		employeeRepository;


	// Constructors -----------------------------------------------------------

	public DepartmentService() {
		super();
	}

	// CRUD ---------------------

	public Department create() {
		Department result;

		result = new Department();

		return result;
	}

	public Collection<Department> findAll() {
		Collection<Department> result;

		result = this.departmentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Department findOne(final int id) {
		Department result;

		result = this.departmentRepository.findOne(id);
		Assert.notNull(result);

		return result;
	}

	public Department save(final Department d) {
		Assert.notNull(d);

		Department result;
		result = this.departmentRepository.save(d);

		Assert.notNull(result);
		return result;
	}

	public void delete(final Department d) {
		Assert.notNull(d);
		Assert.isTrue(d.getId() != 0);
		this.departmentRepository.delete(d);
	}

	public Collection<Employee> getEmployees(final Department d) {
		return this.employeeRepository.getEmployeesByDepartment(d.getId());
	}

	public void addEmployeesToDepartment(final Department d, final Collection<Employee> e_list) {
		final Department department = this.findOne(d.getId());

		for (final Employee e : e_list) {
			final Employee employee = this.employeeRepository.findOne(e.getId());

			if (department.getEmployees().contains(employee) == false) {
				employee.setDepartment(department);
				department.getEmployees().add(employee);
				this.employeeRepository.save(employee);
			}
		}
		this.departmentRepository.save(department);
	}
}
