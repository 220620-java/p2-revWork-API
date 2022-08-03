package p2.revature.revwork.data;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2.revature.revwork.models.data.EmployerData;

@Repository
public interface EmployerRepository extends JpaRepository<EmployerData, Integer> {
	
	public List<EmployerData> findByName(String username);
	
	public EmployerData findById(int id);

	public List<EmployerData> findByUsername(@NotNull String username);
	


}
