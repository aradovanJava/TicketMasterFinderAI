package hr.ticketmaster.finder.ai.ticketmasterfinderai.dto;

import hr.ticketmaster.finder.ai.ticketmasterfinderai.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private String type;
    private String eventName;
    private String venue;
    private String description;
    private String eventDateTimeString;
    private BigDecimal price;
}
