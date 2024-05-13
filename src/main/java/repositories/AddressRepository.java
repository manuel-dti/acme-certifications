package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    // Método para buscar direcciones asociadas a un empleado específico
    @Query("select a from Address a join a.employees e where e.id = ?1")
    Collection<Address> findAddressesByEmployeeId(int employeeId);
}
