package de.miinoo.ultimateticket.configuration.configs;

import com.google.gson.Gson;
import de.miinoo.ultimateticket.model.Ticket;

public class TicketConfiguration  {


    public void saveTicket(Ticket ticket) {
        Gson gson = new Gson();
        gson.toJson(ticket);
    }


}
