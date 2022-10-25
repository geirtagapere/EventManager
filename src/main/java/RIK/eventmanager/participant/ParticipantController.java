package RIK.eventmanager.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@CrossOrigin
@RequestMapping("/participants")
public class ParticipantController {

    // GET      /events        Get all events
    // GET      /events/id     Gets an event
    // POST     /events        Create new event
    // PUT      /events/id     Updates an event
    // DELETE   /events/id     Deletes an event


    @Autowired // declare dependency
    private ParticipantService participantService;


    @GetMapping("/{id}")
    public ModelAndView getParticipantView(@PathVariable(value="id") long participantId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("editParticipant");
        view.addObject("participant", participantService.getParticipantById(participantId).get());
        return view;
    }

    @PutMapping("/{id}/business")
    public ModelAndView editBusinessParticipant(@PathVariable(value="id") long participantId, BusinessParticipant participant) {
        participant.setId(participantId);
        participantService.saveParticipant(participant);
        return new ModelAndView("redirect:/");
    }

    @PutMapping("/{id}/individual")
    public ModelAndView editIndividualParticipant(@PathVariable(value="id") long participantId, IndividualParticipant participant) {
        participant.setId(participantId);
        participantService.saveParticipant(participant);
        return new ModelAndView("redirect:/");
    }

}
