
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Address;
import domain.Employee;
import repositories.AddressRepository;
import repositories.EmployeeRepository;

@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressRepository	addressRepository;

	@Autowired
	private EmployeeRepository	employeeRepository;


	public AddressService() {
		super();
	}

	public Collection<Address> findAll() {
		return this.addressRepository.findAll();
	}

	public Address findOne(final int id) {
		return this.addressRepository.findOne(id);
	}

	public Address save(final Address address) {
		Assert.notNull(address, "The address must not be null");
		return this.addressRepository.save(address);
	}

	public void delete(final Address address) {
		Assert.notNull(address, "The address to delete cannot be null");
		this.addressRepository.delete(address);
	}

	// Método para agregar un empleado a una dirección
	public void addEmployeeToAddress(final int addressId, final int employeeId) {
		final Address managedAddress = this.addressRepository.findOne(addressId);
		final Employee managedEmployee = this.employeeRepository.findOne(employeeId);

		Assert.notNull(managedAddress, "Address not found.");
		Assert.notNull(managedEmployee, "Employee not found.");

		managedAddress.getEmployees().add(managedEmployee);
		managedEmployee.getAddresses().add(managedAddress);

		this.addressRepository.save(managedAddress);
		this.employeeRepository.save(managedEmployee);
	}

	// Método para remover un empleado de una dirección
	public void removeEmployeeFromAddress(final int addressId, final int employeeId) {
		final Address managedAddress = this.addressRepository.findOne(addressId);
		final Employee managedEmployee = this.employeeRepository.findOne(employeeId);

		Assert.notNull(managedAddress, "Address not found.");
		Assert.notNull(managedEmployee, "Employee not found.");

		managedAddress.getEmployees().remove(managedEmployee);
		managedEmployee.getAddresses().remove(managedAddress);

		this.addressRepository.save(managedAddress);
		this.employeeRepository.save(managedEmployee);
	}

	// Método para encontrar direcciones por empleado
	public Collection<Address> findAddressesByEmployee(final int employeeId) {
		return this.addressRepository.findAddressesByEmployeeId(employeeId);
	}
}
