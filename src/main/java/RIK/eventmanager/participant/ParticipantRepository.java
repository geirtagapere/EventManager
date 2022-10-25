package RIK.eventmanager.participant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

    List<Participant> findAll();

    List<IndividualParticipant> findByIdNumber(String idNumber);

    List<BusinessParticipant> findByRegCode(String regCode);

}
