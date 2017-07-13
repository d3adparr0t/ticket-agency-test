package com.alchesoft.training.jboss.producers;


import com.alchesoft.training.jboss.DataManager;
import com.alchesoft.training.jboss.entities.Seat;
import com.alchesoft.training.jboss.entities.SeatType;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class SeatProducer implements Serializable {

    @Inject
    private DataManager seatRepository;

    private List<Seat> seats;

    @Produces
    @Named
    public List<Seat> getSeats() {
        return seats;
    }

    public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
        retrieveData();
    }

    @PostConstruct
    public void retrieveData() {
        seats = seatRepository.findAllSeats();
    }
}
