package hr.ticketmaster.finder.ai.ticketmasterfinderai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Integer id;
    private TicketType type;
    private String eventName;
    private String venue;
    private String description;
    private LocalDateTime eventDateTime;
    private BigDecimal price;

    public Ticket(TicketType type, String eventName, String venue, String description, LocalDateTime eventDateTime,
                  BigDecimal price)
    {
        this.type = type;
        this.eventName = eventName;
        this.venue = venue;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.price = price;
    }
}
