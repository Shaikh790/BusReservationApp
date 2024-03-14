package com.reservationapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;
    @Column(name="bus_id",unique = true,nullable = false)
    private long busId;
//    @OneToOne // Change from @OneToMany to @ManyToOne
//    @JoinColumn(name = "bus_id") // Specify the join column
//    private Bus bus;
//
//    @OneToMany(mappedBy = "route") // Change "bus_id" to "route" to match the field name in SubRoute
//    private List<SubRoute> subRoute;
}
