
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select e from Employee e where e.department.id = ?1")
	public Collection<Employee> getEmployeesByDepartment(int departmentid);

	@Query("select e from Employee e join e.addresses a where a.id = ?1")
	Collection<Employee> findEmployeesByAddressId(int addressId);
}
