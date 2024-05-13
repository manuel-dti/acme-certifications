
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Address;
import domain.Department;
import domain.Employee;
import repositories.AddressRepository;
import repositories.DepartmentRepository;
import repositories.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	// Inyección del repositorio de Employee
	@Autowired
	private EmployeeRepository		_employeeRepository;

	@Autowired
	private DepartmentRepository	_departmentRepository;

	@Autowired
	private AddressRepository		_addressRepository;


	// Constructor
	public EmployeeService() {
		super();
	}

	// Método para crear una nueva instancia de Employee
	public Employee create() {
		final Employee employee = new Employee();
		// Puedes establecer valores por defecto si es necesario
		return employee;
	}

	// Método para encontrar todos los Employees
	public Collection<Employee> findAll() {
		return this._employeeRepository.findAll();
	}

	// Método para encontrar un Employee por ID
	public Employee findOne(final int id) {
		return this._employeeRepository.findOne(id); // Asegúrate de manejar el caso de valor nulo
	}

	// Método para guardar o actualizar un Employee
	public Employee save(final Employee employee) {
		Assert.notNull(employee, "The employee must not be null");
		return this._employeeRepository.save(employee);
	}

	// Método para eliminar un Employee
	public void delete(final Employee employee) {
		Assert.notNull(employee, "The employee to delete cannot be null");
		Assert.isTrue(employee.getId() != 0, "Employee must have a valid id to be deleted");
		this._employeeRepository.delete(employee);
	}

	// Método para asignar un departamento a un empleado
	public void assignDepartment(final Employee e, final Department d) {
		Assert.notNull(e, "The employee cannot be null");
		Assert.notNull(d, "The department cannot be null");

		e.setDepartment(d);

		d.getEmployees().add(e);

		this._employeeRepository.save(e);

		this._departmentRepository.save(d);
	}

	public void removeEmployeeFromDepartment(final Employee employee) {
		Assert.notNull(employee, "The employee cannot be null");

		// Cargar el empleado desde la base de datos para asegurar que tiene el estado más reciente
		final Employee managedEmployee = this._employeeRepository.findOne(employee.getId());
		Assert.notNull(managedEmployee, "Employee must exist");

		final Department managedDepartment = managedEmployee.getDepartment();
		if (managedDepartment != null) {
			// Quitar el empleado de la colección de empleados del departamento
			managedDepartment.getEmployees().remove(managedEmployee);

			// Eliminar la referencia del departamento en el empleado
			managedEmployee.setDepartment(null);

			// Guardar los cambios
			this._employeeRepository.save(managedEmployee);
			this._departmentRepository.save(managedDepartment);
		}
	}

	// Método para asignar dirección a un empleado
	public void addAddressToEmployee(final Employee employee, final Address address) {
		Assert.notNull(employee, "The employee cannot be null");
		Assert.notNull(address, "The address cannot be null");

		final Employee managedEmployee = this._employeeRepository.findOne(employee.getId());
		final Address managedAddress = this._addressRepository.findOne(address.getId());

		managedEmployee.getAddresses().add(managedAddress);
		managedAddress.getEmployees().add(managedEmployee);

		this._employeeRepository.save(managedEmployee);
	}

	// Método para remover dirección de un empleado
	public void removeAddressFromEmployee(final Employee employee, final Address address) {
		Assert.notNull(employee, "The employee cannot be null");
		Assert.notNull(address, "The address cannot be null");

		final Employee managedEmployee = this._employeeRepository.findOne(employee.getId());
		final Address managedAddress = this._addressRepository.findOne(address.getId());

		managedEmployee.getAddresses().remove(managedAddress);
		managedAddress.getEmployees().remove(managedEmployee);

		this._employeeRepository.save(managedEmployee);
	}

	public void addAddressesToEmployee(final Employee employee, final Collection<Address> addresses) {
		Assert.notNull(employee, "The employee cannot be null");
		Assert.notNull(addresses, "The address cannot be null");

		final Employee managedEmployee = this._employeeRepository.findOne(employee.getId());
		Assert.notNull(managedEmployee, "The employee cannot be null");

		for (final Address address : addresses) {
			final Address managedAddress = this._addressRepository.findOne(address.getId());
			Assert.notNull(managedAddress, "The address cannot be null");

			managedEmployee.getAddresses().add(managedAddress);
			managedAddress.getEmployees().add(managedEmployee);
			this._employeeRepository.save(managedEmployee);
		}
	}

	// Método para encontrar empleados por dirección
	public Collection<Employee> findEmployeesByAddress(final int addressId) {
		return this._employeeRepository.findEmployeesByAddressId(addressId);
	}

}
