package is296.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import is296.crm.vo.PotentialLead;

public interface PotentialLeadRepository extends JpaRepository<PotentialLead, String> {
	// "findByXxxx generates the query: "SELECT * FROM POTENTIAL_LEADS WHERE XX = 'myXXParam'"
	PotentialLead findByid(String id);
	List<PotentialLead> findByCity(String city);
	
	@Query(value = "SELECT * FROM POTENTIAL_LEADS "
			+ "WHERE EMPLOYEE_COUNT = (SELECT MAX(EMPLOYEE_COUNT) FROM POTENTIAL_LEADS)", nativeQuery = true)
	List<PotentialLead> getMaxEmployees();
	
	@Query(value = "SELECT * FROM POTENTIAL_LEADS "  
			+ "WHERE ID =:plId OR EMPLOYEE_COUNT = '15'", nativeQuery = true)
	List<PotentialLead> getSomeResults(@Param("plId") String plId); 
}