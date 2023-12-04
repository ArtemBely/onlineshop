package com.example.bookingmodel.data.entity;

import javax.persistence.Entity;
import javax.persistence.*;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity(name = "KosikEntity")
@Builder(toBuilder = true)
@Table(name = "kosik")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
@ToString
public class KosikEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    private List<Integer> productIds;
}
