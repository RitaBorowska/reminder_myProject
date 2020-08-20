package org.example.reminder_my_project.project;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class CarReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int remainderID;

    private ReminderType type;
    private String amount;

    private LocalDateTime date;
    private ReminderPeriod period;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Car car;

    public CarReminder(ReminderType type, String amount, LocalDateTime date, ReminderPeriod period) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.period = period;


    }

}
