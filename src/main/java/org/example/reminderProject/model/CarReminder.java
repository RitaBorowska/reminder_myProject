package org.example.reminderProject.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class CarReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRem;

    @Enumerated(value = EnumType.STRING)
    private CarReminderType type;
    private int amount;

    private LocalDate date;
    @Enumerated(value = EnumType.STRING)
    private ReminderPeriod period;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Car car;

    public CarReminder(CarReminderType type, int amount, LocalDate date, ReminderPeriod period) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.period = period;
    }

    public void setCar(Car car) {

    }

//    public void setIdRem(Long idRem) {
//        this.idRem = idRem;
//    }
//
//    public void setType(ReminderType type) {
//        this.type = type;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//
//    public void setPeriod(ReminderPeriod period) {
//        this.period = period;
//    }
//
//    public void setCar(Car car) {
//        this.car = car;
//    }
}
