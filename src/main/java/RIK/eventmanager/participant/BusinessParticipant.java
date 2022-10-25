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
@DiscriminatorValue("BusinessParticipant")
@Entity (name = "business_participant")
public class BusinessParticipant extends Participant {

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "registry_code")
    private String regCode;

    @Column(name = "participant_count")
    private int participantCount;

    @Column(name = "additional_info_business", length = 5000)
    private String additionalInfoBusiness;

}
