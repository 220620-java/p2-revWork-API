package p2.revature.revwork.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2.revature.revwork.models.data.Profile;

@Repository
public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
	
public Profile findById(int id);
}
