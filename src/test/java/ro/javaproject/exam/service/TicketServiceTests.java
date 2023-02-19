package ro.javaproject.exam.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.javaproject.exam.dto.TicketDto;
import ro.javaproject.exam.entity.Ticket;
import ro.javaproject.exam.exceptions.TicketAlreadyExistsException;
import ro.javaproject.exam.helpers.TicketType;
import ro.javaproject.exam.repository.TicketRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTests {
    @InjectMocks
    TicketService ticketService;

    @Mock
    TicketRepository ticketRepository;


    @Test
    @DisplayName("Add ticket test")
    void addTicket() {
        TicketDto ticketDto = new TicketDto("Title", TicketType.CONCERT, LocalDate.of(2023, 2, 22), "venue", 2);

        Ticket ticket = Ticket.builder()
                .eventTitle("Title")
                .type(TicketType.CONCERT)
                .eventDate(LocalDate.of(2023, 2, 22))
                .eventVenue("venue")
                .price(2)
                .build();

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.addTicket(ticketDto);

        assertEquals("Title", result.getEventTitle());
    }

    @Test
    @DisplayName("Add ticket throws test")
    void addTicketThrows() {
        String expected = "There is already a ticket with this data";
        TicketDto ticketDto = new TicketDto("Title", TicketType.SPORT_COMPETITION, LocalDate.of(2023, 2, 22), "venue", 2);

        Ticket ticket = Ticket.builder()
                .eventTitle("Title")
                .type(TicketType.SPORT_COMPETITION)
                .eventDate(LocalDate.of(2023, 2, 22))
                .eventVenue("venue")
                .price(2)
                .build();

        when(ticketRepository.findTicketByTypeAndEventDateAndEventVenue(ticket.getType(), ticket.getEventDate(), ticket.getEventVenue())).thenReturn(Optional.of(ticket));

        TicketAlreadyExistsException result =assertThrows(TicketAlreadyExistsException.class,
                () -> ticketService.addTicket(ticketDto));

        assertEquals(expected, result.getMessage());
    }

    @Test
    @DisplayName("Get tickets by date test")
    void getTicketsByDate() {
        Ticket ticket = Ticket.builder()
                .eventTitle("Title")
                .type(TicketType.SPORT_COMPETITION)
                .eventDate(LocalDate.of(2023, 2, 22))
                .eventVenue("venue")
                .price(2)
                .build();

        when(ticketRepository.findAllByEventDate(ticket.getEventDate())).thenReturn(Optional.of(List.of(ticket)));

        List<Ticket> result = ticketService.getTickets(ticket.getEventDate(), Optional.empty());

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Get tickets by date and venue test")
    void getTicketsByDateAndVenue() {
        Ticket ticket = Ticket.builder()
                .eventTitle("Title")
                .type(TicketType.SPORT_COMPETITION)
                .eventDate(LocalDate.of(2023, 2, 22))
                .eventVenue("venue")
                .price(2)
                .build();

        when(ticketRepository.findByEventDateAndEventVenue(ticket.getEventDate(), ticket.getEventVenue())).thenReturn(Optional.of(List.of(ticket)));

        List<Ticket> result = ticketService.getTickets(ticket.getEventDate(), Optional.of(ticket.getEventVenue()));

        assertEquals(1, result.size());
    }

}
