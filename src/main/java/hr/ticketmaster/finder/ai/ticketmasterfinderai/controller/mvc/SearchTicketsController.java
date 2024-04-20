package hr.ticketmaster.finder.ai.ticketmasterfinderai.controller.mvc;

import hr.ticketmaster.finder.ai.ticketmasterfinderai.dto.TicketDTO;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.dto.TicketFormDTO;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketSearchForm;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketType;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/mvc")
@AllArgsConstructor
@SessionAttributes({"ticketTypeList", "tickets"})
public class SearchTicketsController {

    private TicketService ticketService;

    @GetMapping("/ticket")
    public String fetchAllTickets(Model model) {

        if(!model.containsAttribute("tickets")) {
            model.addAttribute("tickets",
                    ticketService.findAll());
        }

        if(!model.containsAttribute("ticketTypeList")) {
            model.addAttribute("ticketTypeList",
                    TicketType.values());
        }

        model.addAttribute("ticketFormDTO",
                new TicketFormDTO());

        return "tickets";
    }

    @PostMapping("/ticket")
    public String filterTickets(Model model, TicketFormDTO ticketFormDTO) {
        List<TicketDTO> ticketDTOList = ticketService.filterByCriteria(ticketFormDTO);
        model.addAttribute("tickets", ticketDTOList);
        return "redirect:/mvc/ticket";
    }

}
