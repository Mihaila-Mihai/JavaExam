package ro.javaproject.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.javaproject.exam.helpers.TicketType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String eventTitle;
    private TicketType type;
    private LocalDate eventDate;
    private String eventVenue;
    private double price;

    @Builder
    public Ticket(String eventTitle, TicketType type, LocalDate eventDate, String eventVenue, double price) {
        this.eventTitle = eventTitle;
        this.type = type;
        this.eventDate = eventDate;
        this.eventVenue = eventVenue;
        this.price = price;
    }
}
