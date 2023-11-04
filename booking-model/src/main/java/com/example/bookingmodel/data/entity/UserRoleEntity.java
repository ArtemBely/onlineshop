package com.example.bookingmodel.data.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name = "UserRoleEntity")
@Builder(toBuilder = true)
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class UserRoleEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    @NonNull
    private int user_id;

    @Column(name = "role_id", nullable = false)
    @NonNull
    private int role_id;
}
