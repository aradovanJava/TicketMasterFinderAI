package hr.ticketmaster.finder.ai.ticketmasterfinderai.service;

import hr.ticketmaster.finder.ai.ticketmasterfinderai.utils.ConversionUtils;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.dto.TicketDTO;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.dto.TicketFormDTO;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.Ticket;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketFilter;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketType;
import hr.ticketmaster.finder.ai.ticketmasterfinderai.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    @Override
    public List<TicketDTO> findAll() {
        return ticketRepository.findAll()
                .stream().map(this::convertTicketToTicketDto)
                .toList();
    }

    @Override
    public Optional<TicketDTO> findById(Integer id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        return ticketOptional.map(this::convertTicketToTicketDto);
    }

    @Override
    public void save(TicketDTO ticketDTO) {
        ticketRepository.save(convertTicketDtoToTicket(ticketDTO));
    }

    @Override
    public List<TicketDTO> filterByCriteria(TicketFormDTO ticketFormDTO) {
        return ticketRepository.filterByCriteria(convertTicketDtoToTicketFilter(ticketFormDTO))
                .stream()
                .map(this::convertTicketToTicketDto)
                .toList();
    }

    private TicketFilter convertTicketDtoToTicketFilter(TicketFormDTO ticketFormDTO) {
        return new TicketFilter(
                ticketFormDTO.getType(),
                ticketFormDTO.getEventName(),
                ticketFormDTO.getVenue(),
                ticketFormDTO.getDescription(),
                ticketFormDTO.getEventDateTimeFromString(),
                ticketFormDTO.getEventDateTimeToString(),
                ticketFormDTO.getPriceFrom(),
                ticketFormDTO.getPriceTo());
    }

    private Ticket convertTicketDtoToTicket(TicketDTO ticketDTO) {
        return new Ticket(TicketType.valueOf(ticketDTO.getType()),
                            ticketDTO.getEventName(),
                            ticketDTO.getVenue(),
                            ticketDTO.getDescription(),
                            LocalDateTime.parse(ticketDTO.getEventDateTimeString(), ConversionUtils.FORMATTER),
                            ticketDTO.getPrice()
                );
    }

    private TicketDTO convertTicketToTicketDto(Ticket ticket) {
        return new TicketDTO(ticket.getType().name(),
                                ticket.getEventName(),
                                ticket.getVenue(),
                                ticket.getDescription(),
                                ticket.getEventDateTime().format(ConversionUtils.FORMATTER),
                                ticket.getPrice()
        );
    }
}
