package ro.javaproject.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.javaproject.exam.entity.Ticket;
import ro.javaproject.exam.helpers.TicketType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findTicketByTypeAndEventDateAndEventVenue(TicketType ticketType, LocalDate eventDate, String eventVenue);
    Optional<List<Ticket>> findAllByEventDate(LocalDate eventDate);
    Optional<List<Ticket>> findByEventDateAndEventVenue(LocalDate eventDate, String eventVenue);
}
