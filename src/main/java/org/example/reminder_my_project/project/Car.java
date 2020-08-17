package org.example.reminder_my_project.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String registerNum;
    private String mark;

    private String model;

    public Car(String registerNum, String mark, String model) {
        this.registerNum = registerNum;
        this.mark = mark;
        this.model = model;
    }

    @OneToMany(mappedBy = "car")
    private List<CarReminder> carReminder;
    
}
