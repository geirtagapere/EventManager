package RIK.eventmanager.event;

import RIK.eventmanager.participant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
public class EventController {

    @Autowired // declare dependency
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantService participantService;

    @GetMapping({"/","index"})
    public ModelAndView landingPageView() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        view.addObject("eventList", eventService.getAllEvents());
        return view;
    }

    @GetMapping("/events")
    public ModelAndView addEvent(Model model) {
        model.addAttribute("events", new Event());
        return new ModelAndView("addevent");
    }

    @PostMapping("/events")
    public ModelAndView addEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/events/{id}")
    public ModelAndView viewevent(@PathVariable long id) {
        ModelAndView view = new ModelAndView();
        List<ParticipantListItem> participantListItems = new ArrayList<>();
        for (Participant participant : eventService.getEvent(id).get().getParticipants()) {
            if (participant instanceof BusinessParticipant) {
                BusinessParticipant businessParticipant = (BusinessParticipant) participant;
                participantListItems.add(new ParticipantListItem(businessParticipant.getId(),
                                businessParticipant.getLegalName(), businessParticipant.getRegCode()));
            } else if (participant instanceof  IndividualParticipant) {
                IndividualParticipant individualParticipant = (IndividualParticipant)  participant;
                participantListItems.add(new ParticipantListItem(individualParticipant.getId(),
                        individualParticipant.getFirstName() + " " + individualParticipant.getLastName(),
                        individualParticipant.getIdNumber()));
            }
        }
        view.setViewName("viewevent");
        view.addObject("oneEvent", eventService.getEvent(id).get());
        view.addObject("individualParticipant", new IndividualParticipant());
        view.addObject("businessParticipant", new BusinessParticipant());
        view.addObject("allParticipants", participantListItems);
        return view;
    }

    @PostMapping("/events/{id}/participants/individual")
    public ModelAndView addIndividualParticipant(@PathVariable long id, IndividualParticipant individualParticipant) {

        List<IndividualParticipant> individualsByIdNumber = participantService.getParticipantByIdNumber(individualParticipant.getIdNumber());
        Event event = eventService.getEvent(id).get();

        if (individualsByIdNumber.isEmpty()) {
            individualParticipant = (IndividualParticipant) participantService.saveParticipant(individualParticipant);
            event.getParticipants().add(individualParticipant);
            eventService.saveEvent(event);
        } else {
            Participant foundParticipantWithID = individualsByIdNumber.get(0);
            if (event.getParticipants().stream().noneMatch(e -> e.getId() == foundParticipantWithID.getId())) {
                event.getParticipants().add(foundParticipantWithID);
                eventService.saveEvent(event);
            }
        }
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/events/{id}/participants/business")
    public ModelAndView addBusinessParticipant(@PathVariable long id, BusinessParticipant businessParticipant) {

        List<BusinessParticipant> businessParticipantsByRegCode = participantService.getParticipantByRegCode(businessParticipant.getRegCode());
        Event event = eventService.getEvent(id).get();

        if (businessParticipantsByRegCode.isEmpty()) {
            businessParticipant = (BusinessParticipant) participantService.saveParticipant(businessParticipant);

            event.getParticipants().add(businessParticipant);
            eventService.saveEvent(event);
        } else {
            Participant foundParticipantWithRegCode = businessParticipantsByRegCode.get(0);
            if (event.getParticipants().stream().noneMatch(e -> e.getId() == foundParticipantWithRegCode.getId())) {
                event.getParticipants().add(foundParticipantWithRegCode);
                eventService.saveEvent(event);
            }
        }
        return new ModelAndView("redirect:/");
    }

    // deletemapping tootaks l2bi formi ||| <a> ainult .GETib
    @GetMapping("/events/{id}/delete")
    public ModelAndView deleteEvent(@PathVariable(value="id") long id) {
        eventService.deleteEvent(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/events/{eventId}/participants/{id}/delete")
    public ModelAndView deleteParticipantFromEvent(@PathVariable(value="id") long participantId, @PathVariable(value="eventId") long eventId) {
        Event event = eventService.getEvent(eventId).get();
        List<Participant> participants = event.getParticipants().stream()
                .filter(p -> p.getId() != participantId)
                .collect(Collectors.toList());
        event.setParticipants(participants);
        eventService.saveEvent(event);

        return new ModelAndView("redirect:/events/" + eventId);
    }

}
