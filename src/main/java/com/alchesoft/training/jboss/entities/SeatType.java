package com.alchesoft.training.jboss.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="seat_type")
public class SeatType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min=1, max=25, message="Enter a seat description (max 25 chars)")
    @Pattern(regexp = "[A-Za-z][A-Za-z ]*", message = "Use only letters and spaces")
    private String description;
    @NotNull
    private Integer price;
    @NotNull
    private Integer quantity;

    @OneToMany(mappedBy = "seatType", fetch = FetchType.EAGER)
    private List<Seat> seats;
}
