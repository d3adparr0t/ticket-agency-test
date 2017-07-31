package com.alchesoft.training.jboss.controller;

import com.alchesoft.training.jboss.entities.SeatType;
import com.alchesoft.training.jboss.service.TicketService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model
public class TicketController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private TicketService ticketService;

    @Inject
    private List<SeatType> seatTypes;

    @Inject
    private Conversation conversation;

    @Produces
    @Named
    private SeatType newSeatType;

    @PostConstruct
    public void initSeatType() {
        newSeatType = new SeatType();
    }

    public String createTheatre() throws Exception {
        ticketService.createTheatre(seatTypes);
        conversation.begin();
        return "book";
    }

    public String restart() {
        ticketService.cleanup();
        conversation.end();
        return "/index";
    }

    public void create() {
        try {
            ticketService.createSeatType(newSeatType);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Done"
                    , "Seats added");
            facesContext.addMessage(null, msg);
            initSeatType();
        } catch (Exception e) {
            String error = getRootErrorMessage(e);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    error,
                    "Error while saving data");
            facesContext.addMessage(null, msg);
        }
    }

    private String getRootErrorMessage(Throwable t) {
        String errorMsg = "Registration failed. See server log for more information";
        while (t != null) {
            errorMsg = t.getMessage();
            t = t.getCause();
        }
        return errorMsg;
    }

}
