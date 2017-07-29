package com.alchesoft.training.jboss.service;

import com.alchesoft.training.jboss.DataManager;
import com.alchesoft.training.jboss.entities.Seat;
import com.alchesoft.training.jboss.entities.SeatType;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class TicketService implements Serializable {

    @Inject
    private Logger log;

    @Inject
    private Event<SeatType> seatTypeEvent;

    @Inject
    private Event<Seat> seatEvent;

    @Inject
    private DataManager repository;

    public void createSeatType(SeatType seatType) throws Exception {
        log.info("Registering " + seatType.getDescription());
        repository.save(seatType);
        seatTypeEvent.fire(seatType);
    }

    public void createTheatre(List<SeatType> seatTypes) throws Exception {
        for (SeatType st : seatTypes) {
            for (int i=0;i<st.getQuantity();i++) {
                Seat seat = Seat.builder().booked(false).seatType(st).build();
                repository.save(seat);
            }
        }
    }

    public void bookSeat(Long seatId) {
        Seat seat = repository.findById(seatId);
        seat.setBooked(true);
        repository.save(seat);
        seatEvent.fire(seat);
    }

    public void cleanup() {
        repository.deleteAllData();
    }
}
