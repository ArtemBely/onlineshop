package com.example.bookingmodel.repositories;

import com.example.bookingmodel.data.entity.KosikEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KosikRepository extends JpaRepository<KosikEntity, Integer> {
}
