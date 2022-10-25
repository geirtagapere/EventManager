package RIK.eventmanager.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class EventServiceTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void saveEventTest() {

        String time5 = "2025-04-07 12:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Event event = Event.builder()
                .eventName("test yritus")
                .dateTime(LocalDateTime.parse(time5,formatter))
                .venue("Viimsi keskus")
                .additionalInfo("yrituse info")
                .build();

        eventRepository.save(event);
        Assertions.assertTrue(event.getId() > 0);
    }

}
