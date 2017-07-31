package com.alchesoft.training.jboss.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ConversationScoped
public class BookerService implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private TicketService ticketService;

    private int amount;

    @PostConstruct
    public void createCustomer() {
        this.amount = 100;
    }

    public void bookSeat(Long seatId, int price) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (price > amount) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Not enough money!",
                    "Registration successful");
            fc.addMessage(null, msg);
            return;
        }
        logger.info("Booking seat " + seatId);
        ticketService.bookSeat(seatId);
        amount -= price;

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Registered",
                "Registration successful");
        fc.addMessage(null, msg);
        logger.info("Seat booked.");
    }

    public int getAmount() {
        return amount;
    }
}
