package RIK.eventmanager.bootstrap;

import RIK.eventmanager.event.Event;
import RIK.eventmanager.event.EventRepository;
import RIK.eventmanager.participant.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *      Siin lisan andmebaasi mõned üritused
 */
public class Bootstrap implements ApplicationRunner {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (eventRepository.findAll().size() == 0) {
            String time1 = "2016-04-25 11:30";
            String time2 = "2019-10-15 16:00";
            String time3 = "2023-08-30 18:30";
            String time4 = "2024-02-14 19:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Event e1 = eventRepository.save(new Event("Esimene ev", LocalDateTime.parse(time1,formatter), "Vallikäär", "Toimub aasta parim pidu"));
            Event e2 = eventRepository.save(new Event("Teine ev", LocalDateTime.parse(time2,formatter), "Lauluväljak", "Ernesaksad möllud särgidd"));
            Event e3 = eventRepository.save(new Event("Kolmas ev", LocalDateTime.parse(time3,formatter), "Reidi tee", "Piilupardi rongkäik"));
            Event e4 = eventRepository.save(new Event("Neljas ev", LocalDateTime.parse(time4,formatter), "Lennuväli", "Lennukid tulevad ja lähevad"));
        }

        /*
        // INDIVIDUAL
        "Sini", "Lille", 12345678913,  "info1 1500char"
        "Juhan", "Juurikas", 21234567890, "info2 1500char"
        "Maiu", "Maasikas", 33456789012, "info3 1500char"
        "Tõnu", "Tare", 44567890123, "info4 1500char"
        "Visna", "Puu", 55678901234, "info5 1500char"
        "Maasi", "Kass", 66789012345, "info6 1500char"
        "Vaari", "Kass", 77890123456, "info7 1500char"

        // BUSINESS
        "Ehitajate OÜ", 12345678, 4, "bp1 5k char"
        "Maakler OÜ", 23456789, 15, "bp2 5k char"
        "Kalur OÜ", 34567891, 2, "bp3 5k char"
        "Pottsepp OÜ", 45678912, 20, "bp4 5k char"
        "Lendur OÜ", 56789123, 11, "bp5 5k char"
        */
    }
}