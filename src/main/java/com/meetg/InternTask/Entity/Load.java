package com.meetg.InternTask.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Load {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String shipperId;

    @Embedded
    private Facility facility;

    private String productType;
    private String truckType;
    private Integer noOfTrucks;
    private Double weight;
    private String comment;

    @CreationTimestamp
    private Timestamp datePosted;

    @Enumerated(EnumType.STRING)
    private LoadStatus status = LoadStatus.POSTED;

    @OneToMany(mappedBy = "load", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}

