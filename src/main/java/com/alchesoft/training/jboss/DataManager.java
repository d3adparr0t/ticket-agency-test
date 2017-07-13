package com.alchesoft.training.jboss;


import com.alchesoft.training.jboss.entities.Seat;
import com.alchesoft.training.jboss.entities.SeatType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DataManager {

    @Inject
    private EntityManager em;

    public Seat findById(Long id) {
        return em.find(Seat.class, id);
    }

    public List<SeatType> findAllSeatTypes() {
        return em.createQuery("from SeatType st", SeatType.class).getResultList();
    }

    public List<Seat> findAllSeats() {
        return em.createQuery("from Seat s", Seat.class).getResultList();
    }

    public void deleteAllData() {
        em.createQuery("delete from Seat").executeUpdate();
        em.createQuery("delete from SeatType").executeUpdate();
    }

    public void save(Object entity) {
        em.persist(entity);
    }
}
