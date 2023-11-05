package com.example.bookingmodel.data.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity(name = "RoleEntity")
@Builder(toBuilder = true)
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Setter
@Getter
@Component
@ToString
public class RoleEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name", nullable = false)
    private String role_name;

    @Column(name = "role_description", nullable = false)
    private String role_description;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private List<CustomerEntity> customerEntities;
}
