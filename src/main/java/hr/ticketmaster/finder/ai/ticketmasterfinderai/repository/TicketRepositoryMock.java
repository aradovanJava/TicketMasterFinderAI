package hr.ticketmaster.finder.ai.ticketmasterfinderai.repository;

import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.Ticket;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketFilter;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketType;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.utils.ConversionUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TicketRepositoryMock implements TicketRepository {

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

        Ticket secondTicket = new Ticket();
        secondTicket.setId(2);
        secondTicket.setType(TicketType.SPORT);
        secondTicket.setEventName("FOOTBALL MATCH: HAJDUK - LOKOMOTIVA");
        secondTicket.setVenue("Stadion Poljud (posljednja utakmica)");
        secondTicket.setDescription("Rezultat je poznat");
        secondTicket.setEventDateTime(LocalDateTime.now().plusDays(10));
        secondTicket.setPrice(new BigDecimal("21"));

        Ticket thirdTicket = new Ticket();
        thirdTicket.setId(3);
        thirdTicket.setType(TicketType.SPORT);
        thirdTicket.setEventName("FOOTBALL MATCH: SLAVEN BELUPO - RIJEKA");
        thirdTicket.setVenue("Stadion Gradski (posljednja utakmica)");
        thirdTicket.setDescription("Rezultat je poznat");
        thirdTicket.setEventDateTime(LocalDateTime.now().plusDays(20));
        thirdTicket.setPrice(new BigDecimal("22"));

        Ticket fourthTicket = new Ticket();
        fourthTicket.setId(4);
        fourthTicket.setType(TicketType.SPORT);
        fourthTicket.setEventName("FOOTBALL MATCH: DINAMO ZAGREB - RUDEŠ");
        fourthTicket.setVenue("Stadion Maksimir (posljednja utakmica)");
        fourthTicket.setDescription("Rezultat je poznat");
        fourthTicket.setEventDateTime(LocalDateTime.now().plusDays(30));
        fourthTicket.setPrice(new BigDecimal("23"));

        Ticket fifthTicket = new Ticket();
        fifthTicket.setId(5);
        fifthTicket.setType(TicketType.ART);
        fifthTicket.setEventName("NUTCRACKER");
        fifthTicket.setVenue("Hrvatsko narodno kazalište");
        fifthTicket.setDescription("Božićna predstava");
        fifthTicket.setEventDateTime(LocalDateTime.now().plusDays(180));
        fifthTicket.setPrice(new BigDecimal("50"));

        ticketList.add(firstTicket);
        ticketList.add(secondTicket);
        ticketList.add(thirdTicket);
        ticketList.add(fourthTicket);
        ticketList.add(fifthTicket);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketList;
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        return ticketList.stream()
                .filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Ticket ticket) {
        ticketList.add(ticket);
    }

    @Override
    public List<Ticket> filterByCriteria(TicketFilter ticketFilter) {
        List<Ticket> ticketsList = findAll();

        Optional<TicketType> ticketTypOptional = Arrays.stream(TicketType.values())
                .filter(tt -> tt.name().equals(ticketFilter.getType()))
                .findFirst();

        if(ticketTypOptional.isPresent()) {
            ticketsList = ticketsList.stream()
                    .filter(t -> t.getType().name().equals(ticketFilter.getType()))
                            .toList();
        }

        if(!ticketFilter.getEventName().isEmpty()) {
            ticketsList = ticketsList.stream()
                    .filter(t -> t.getEventName().contains(ticketFilter.getEventName()))
                    .toList();
        }

        if(!ticketFilter.getVenue().isEmpty()) {
            ticketsList = ticketsList.stream()
                    .filter(t -> t.getVenue().contains(ticketFilter.getVenue()))
                    .toList();
        }

        if(!ticketFilter.getDescription().isEmpty()) {
            ticketsList = ticketsList.stream()
                    .filter(t -> t.getDescription().contains(ticketFilter.getDescription()))
                    .toList();
        }

        LocalDateTime localDateTimeTo = LocalDateTime.parse(ticketFilter.getEventDateTimeToString(),
                ConversionUtils.FORMATTER);

        if(!ticketFilter.getEventDateTimeFromString().isEmpty()) {
            LocalDateTime localDateTimeFrom = LocalDateTime.parse(ticketFilter.getEventDateTimeFromString(),
                    ConversionUtils.FORMATTER);
            ticketsList = ticketsList.stream()
                    .filter(t -> t.getEventDateTime().isAfter(localDateTimeFrom))
                    .toList();
        }

        return ticketsList;
    }
}
