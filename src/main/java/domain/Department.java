
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Department extends DomainEntity {

	private String	name;
	private String	building;


	// Constructor -------------------------

	public Department() {
		super();

		this.employees = new HashSet<Employee>();
	}

	// Getters and Setters  --------------------

	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getBuilding() {
		return this.building;
	}
	public void setBuilding(final String building) {
		this.building = building;
	}


	// Relations ---------------------------------

	private Collection<Employee> employees;


	@OneToMany(mappedBy = "department")
	public Collection<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(final Collection<Employee> employees) {
		this.employees = employees;
	}

}
