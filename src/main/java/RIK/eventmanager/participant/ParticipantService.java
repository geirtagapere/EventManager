package RIK.eventmanager.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Optional<Participant> getParticipantById(long id) {
        return participantRepository.findById(id);
    }

    public List<IndividualParticipant> getParticipantByIdNumber(String idNumber) {
        return participantRepository.findByIdNumber(idNumber);

    }

    public List<BusinessParticipant> getParticipantByRegCode(String regCode) {
        return participantRepository.findByRegCode(regCode);
    }

    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

}
