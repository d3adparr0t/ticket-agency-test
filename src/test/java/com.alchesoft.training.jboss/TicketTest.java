package com.alchesoft.training.jboss;

import com.alchesoft.training.jboss.entities.Seat;
import com.alchesoft.training.jboss.entities.SeatType;
import com.alchesoft.training.jboss.producers.SeatProducer;
import com.alchesoft.training.jboss.repositories.DataManager;
import com.alchesoft.training.jboss.service.TicketService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;


import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TicketTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "ticket-agency-test.war")
                .addPackage(SeatProducer.class.getPackage())
                .addPackage(Seat.class.getPackage())
                .addPackage(TicketService.class.getPackage())
                .addPackage(DataManager.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private TicketService ticketService;

    @Inject
    private Logger log;

    @Test
    public void createsSeatType() throws Exception {

        // given
        SeatType seatType = SeatType.builder().description("description").price(30).quantity(5).build();

        // when
        ticketService.createSeatType(seatType);
        log.info(String.format("Seat type created %s", seatType.getDescription()));

        // then
        assertNotNull(seatType);
    }
}
