package com.alchesoft.training.jboss.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class Seat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Boolean booked;

    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;
}
