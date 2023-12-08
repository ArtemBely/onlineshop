package com.example.bookingmodel.data.entity;

import com.example.bookingmodel.enums.CategoryProduct;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity(name = "ProductEntity")
@Builder(toBuilder = true)
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Component
@ToString
public class ProductEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nazev", nullable = false)
    @NonNull
    private String nazev;

    @Column(name = "popis", nullable = false)
    @NonNull
    private String popis;

    @Column(name = "vyrobce", nullable = false)
    @NonNull
    private String vyrobce;

    @Column(name = "cena", nullable = false)
    @NonNull
    private double cena;

    @Column(name = "mnozstvi", nullable = false)
    @NonNull
    private int mnozstvi;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    @NonNull
    private CategoryProduct category;

//    @Lob
//    @Column(name = "image", columnDefinition = "BLOB")
//    private byte[] image;


}
