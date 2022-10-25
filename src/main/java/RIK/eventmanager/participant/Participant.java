package RIK.eventmanager.participant;

import RIK.eventmanager.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "participant")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Participant {

    @Id // primary key
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    /*
    @OneToOne
    private IndividualParticipant individualParticipant;

    @OneToOne
    private BusinessParticipant businessParticipant;
*/
    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL) // kui siia midagi salvestan, siis salvestab ka relationi
    private List<Event> events;

    public Participant(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public enum PaymentMethod {
        CASH,
        CARD
    }


}
