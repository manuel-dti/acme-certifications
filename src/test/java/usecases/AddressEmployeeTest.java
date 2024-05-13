
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

import domain.Address;
import domain.Employee;
import services.AddressService;
import services.EmployeeService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressEmployeeTest extends AbstractTest {

	@Autowired
	private AddressService	addressService;

	@Autowired
	private EmployeeService	employeeService;


	// Helper methods to create and save entities
	private Employee createAndSaveEmployee(final String name) {
		final Employee employee = this.employeeService.create();
		employee.setName(name);
		return this.employeeService.save(employee);
	}

	private Address createAndSaveAddress(final String completeAddress) {
		final Address address = new Address();
		address.setCompleteAddress(completeAddress);
		return this.addressService.save(address);
	}

	// Test cases
	@Test
	@Transactional
	public void testAddAndRemoveAddressToEmployee() {
		final Employee employee = this.createAndSaveEmployee("John Doe");
		final Address address = this.createAndSaveAddress("1234 Main St");

		// Test adding address to employee
		this.addressService.addEmployeeToAddress(address.getId(), employee.getId());
		Employee fetchedEmployee = this.employeeService.findOne(employee.getId());
		Assert.isTrue(fetchedEmployee.getAddresses().contains(address), "Address was not added correctly");

		// Test removing address from employee
		this.addressService.removeEmployeeFromAddress(address.getId(), employee.getId());
		fetchedEmployee = this.employeeService.findOne(employee.getId());
		Assert.isTrue(!fetchedEmployee.getAddresses().contains(address), "Address was not removed correctly");
	}

	@Test
	@Transactional
	public void testFindEmployeesByAddress() {
		final Address address = this.createAndSaveAddress("1234 Main St");
		final Employee employee1 = this.createAndSaveEmployee("John Doe");
		final Employee employee2 = this.createAndSaveEmployee("Jane Smith");

		this.addressService.addEmployeeToAddress(address.getId(), employee1.getId());
		this.addressService.addEmployeeToAddress(address.getId(), employee2.getId());

		final Collection<Employee> employees = this.employeeService.findEmployeesByAddress(address.getId());
		Assert.isTrue(employees.size() == 2, "Incorrect number of employees found for address");
		Assert.isTrue(employees.contains(employee1) && employees.contains(employee2), "Employees do not match expected values");
	}

	@Test
	@Transactional
	public void testAddAddressestoEmployee() {
		final Collection<Address> addresses = new HashSet<Address>();

		Employee employee = this.createAndSaveEmployee("John Doe");
		Address address = this.createAndSaveAddress("1234 Main St");

		addresses.add(address);

		address = this.createAndSaveAddress("El Carmen");
		addresses.add(address);

		address = this.createAndSaveAddress("Cantero Cuadrado");
		addresses.add(address);

		this.employeeService.addAddressesToEmployee(employee, addresses);

		employee = this.employeeService.findOne(employee.getId());

		Assert.isTrue(employee.getAddresses().containsAll(addresses));
	}
}
