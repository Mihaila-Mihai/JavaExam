package ro.javaproject.exam.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.javaproject.exam.helpers.TicketType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    @NotNull(message = "Title can not be null")
    @NotBlank(message = "You have to insert a title")
    @Size(max = 200, message = "Title must contain maximum 200 characters")
    private String eventTitle;
    private TicketType type;
    @FutureOrPresent(message = "Date must not be in the past")
    private LocalDate eventDate;
    @NotNull(message = "Venue can not be null")
    @NotBlank(message = "You have to insert a venue")
    @Size(max = 200, message = "Venue must contain maximum 200 characters")
    private String eventVenue;
    @Positive(message = "Price must be greater than zero")
    private double price;
}
