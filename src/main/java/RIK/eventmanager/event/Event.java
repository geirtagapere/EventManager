package RIK.eventmanager.event;
import RIK.eventmanager.participant.Participant;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity (name = "event")
public class Event {

    @Id // primary key
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "event_name")
    public String eventName;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_time")
    public LocalDateTime dateTime;

    @Column(name = "venue")
    private String venue;

    @Column(name = "additional_info")
    private String additionalInfo;

    @ManyToMany
    @JoinTable(name = "rel_event_participant",
                joinColumns = @JoinColumn(name="event_id"),
                inverseJoinColumns = @JoinColumn(name="participant_id"))
    private List<Participant> participants;


    public Event(String eventName, LocalDateTime dateTime, String venue, String additionalInfo) {
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.venue = venue;
        this.additionalInfo = additionalInfo;
    }


}
