package RIK.eventmanager.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("IndividualParticipant") // aitab aru saada, et tegemist on alamklassiga
@Entity (name = "individual_participant")
public class IndividualParticipant extends Participant {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "additional_info", length = 1500)
    private String additionalInfoIndividual;

}
