package hr.ticketmaster.finder.ai.ticketmasterfinderai.controller.mvc;

import hr.ticketmaster.finder.ai.ticketmasterfinderai.dto.TicketDTO;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.Ticket;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketType;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mvc")
@AllArgsConstructor
public class IndexController {

    private TicketService ticketService;

    @GetMapping("/newTicket")
    public String getNewTicketPage(Model model) {
        model.addAttribute("ticketTypeList", TicketType.values());
        model.addAttribute("ticketDTO", new TicketDTO());
        return "newTicket";
    }

    @PostMapping("/newTicket")
    public String saveNewTicket(@ModelAttribute TicketDTO ticketDTO, Model model) {
        ticketService.save(ticketDTO);
        return "redirect:newTicket";
    }

}
