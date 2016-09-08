package cinema.controller;

/**
 * Created by Vitali_Sheuka on 9/7/2016.
 */

import cinema.Application;
import cinema.model.Amount;
import cinema.model.Ticket;
import cinema.model.TicketMessage;
import cinema.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Random;

@Controller
public class BookingController {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final List<Ticket> tickets = new ArrayList<>();

    @MessageMapping("/cinema")
    @SendTo("/topic/ticket")
    public Ticket greeting(TicketMessage message) throws Exception {
        Ticket ticket = new Ticket(DatatypeConverter.printHexBinary(secureRandom.generateSeed(4)),
                Application.getFilm(secureRandom.nextInt(100)),
                System.currentTimeMillis() + 604800000 + secureRandom.nextInt(604800000), 1 + secureRandom.nextInt(100),
                new Amount((double)secureRandom.nextInt(100) + 1, Currency.getInstance("USD")),
                new User(message.getName(), message.getSurname()));
        tickets.add(ticket);
        return ticket;
    }

}
