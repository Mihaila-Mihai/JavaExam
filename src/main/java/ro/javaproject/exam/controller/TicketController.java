package ro.javaproject.exam.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.javaproject.exam.dto.TicketDto;
import ro.javaproject.exam.entity.Ticket;
import ro.javaproject.exam.service.TicketService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> addTicket(@RequestBody @Valid TicketDto ticketDto) {
        Ticket ticket = ticketService.addTicket(ticketDto);
        return ResponseEntity.created(URI.create("/" + ticket.getId())).body(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(@RequestParam @DateTimeFormat LocalDate eventDate, @RequestParam(required = false) String eventVenue) {
        return ResponseEntity.ok(this.ticketService.getTickets(eventDate, Optional.ofNullable(eventVenue)));
    }
}
