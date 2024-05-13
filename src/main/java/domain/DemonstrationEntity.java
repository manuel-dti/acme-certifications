
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class DemonstrationEntity extends DomainEntity {

	private String	name;
	private String	atribute01;


	// Constructor --------------------------------------

	public DemonstrationEntity() {
		super();
	}

	// Getters and Setters ----------------------------------
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getAtribute01() {
		return this.atribute01;
	}
	public void setAtribute01(final String atribute01) {
		this.atribute01 = atribute01;
	}
}
