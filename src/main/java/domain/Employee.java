
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends DomainEntity {

	// Atributos básicos
	private String name;


	// Constructor sin argumentos
	public Employee() {
		super();
		this.addresses = new HashSet<Address>();
	}

	// Getters y setters
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	// Relations ---------------------------------------

	private Department department;


	@ManyToOne(optional = true)
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(final Department department) {
		this.department = department;
	}


	private Collection<Address> addresses;


	@ManyToMany
	public Collection<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(final Collection<Address> addresses) {
		this.addresses = addresses;
	}
}
