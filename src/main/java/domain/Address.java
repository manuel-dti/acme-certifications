
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Address extends DomainEntity {

	private String completeAddress;


	public Address() {
		super();
		this.employees = new HashSet<>();
	}

	public String getCompleteAddress() {
		return this.completeAddress;
	}

	public void setCompleteAddress(final String completeAddress) {
		this.completeAddress = completeAddress;
	}


	// Relations -------------------------------------------
	private Collection<Employee> employees;


	@ManyToMany(mappedBy = "addresses")
	public Collection<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(final Collection<Employee> employees) {
		this.employees = employees;
	}
}
