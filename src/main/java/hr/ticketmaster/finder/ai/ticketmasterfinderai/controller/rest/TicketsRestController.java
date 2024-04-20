package hr.ticketmaster.finder.ai.ticketmasterfinderai.controller.rest;

import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.Ticket;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest")
public class TicketsRestController {

    private static List<Ticket> ticketList;

    static {
        ticketList = new ArrayList<>();

        Ticket firstTicket = new Ticket();
        firstTicket.setId(1);
        firstTicket.setType(TicketType.SPORT);
        firstTicket.setEventName("FOOTBALL MATCH: DINAMO ZAGREB - LOKOMOTIVA");
        firstTicket.setVenue("Stadion Maksimir (posljednja utakmica)");
        firstTicket.setDescription("Rezultat je poznat");
        firstTicket.setEventDateTime(LocalDateTime.now().plusDays(1));
        firstTicket.setPrice(new BigDecimal("20"));

        ticketList.add(firstTicket);
    }

    @GetMapping("/tickets")
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable Integer id) {
        return ticketList.stream()
                .filter(t -> t.getId().compareTo(id) == 0)
                .toList().getFirst();
    }

    @PostMapping("/ticket")
    public void createNewTicket(@RequestBody Ticket ticket) {
        ticketList.add(ticket);
    }

}
