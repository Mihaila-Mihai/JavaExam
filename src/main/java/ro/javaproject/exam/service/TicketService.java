package ro.javaproject.exam.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.javaproject.exam.dto.TicketDto;
import ro.javaproject.exam.entity.Ticket;
import ro.javaproject.exam.exceptions.TicketAlreadyExistsException;
import ro.javaproject.exam.helpers.TicketType;
import ro.javaproject.exam.repository.TicketRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket addTicket(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .eventTitle(ticketDto.getEventTitle())
                .type(ticketDto.getType())
                .eventDate(ticketDto.getEventDate())
                .eventVenue(ticketDto.getEventVenue())
                .price(ticketDto.getPrice())
                .build();
        if (ticket.getType() == TicketType.SPORT_COMPETITION) {
            Optional<Ticket> existingTicket = this.ticketRepository.findTicketByTypeAndEventDateAndEventVenue(
                    ticket.getType(),
                    ticket.getEventDate(),
                    ticket.getEventVenue()
            );
            if (existingTicket.isPresent()) {
                throw new TicketAlreadyExistsException("There is already a ticket with this data");
            }
        }

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTickets(LocalDate eventDate, Optional<String> eventVenue) {
        Optional<List<Ticket>> result;
        if (eventVenue.isEmpty()) {
            result = this.ticketRepository.findAllByEventDate(eventDate);
        } else {
            result = this.ticketRepository.findByEventDateAndEventVenue(eventDate, eventVenue.get());
        }
        return result.orElseGet(ArrayList::new);
    }
}
