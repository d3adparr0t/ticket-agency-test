package com.alchesoft.training.jboss.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
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
