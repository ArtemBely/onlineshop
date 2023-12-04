package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.KosikDTO;
import com.example.bookingmodel.data.entity.KosikEntity;
import com.example.bookingmodel.data.entity.ProductEntity;
import com.example.bookingmodel.data.mapper.KosikMapper;
import com.example.bookingmodel.repositories.KosikRepository;
import com.example.bookingmodel.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class KosikService {
    private final KosikRepository kosikRepository;
    private final ProductRepository productRepository;

    private final KosikMapper kosikMapper;

    public KosikDTO addProductToKosik(int kosikId, int productId) {
        KosikEntity kosik = kosikRepository.findById(kosikId)
                .orElseGet(() -> {
                    KosikEntity newKosik = new KosikEntity();
                    newKosik.setProductIds(new ArrayList<>());
                    return newKosik;
                });

        List<Integer> productIds = kosik.getProductIds();
        productIds.add(productId);
        kosik.setProductIds(productIds);
        kosik = kosikRepository.save(kosik);

        return kosikMapper.mapToDto(kosik);
    }

    public KosikDTO removeProductFromKosik(int kosikId, int productId) {
        KosikEntity kosik = kosikRepository.findById(kosikId)
                .orElseThrow(() -> new RuntimeException("Kosik not found"));
        List<Integer> productIds = kosik.getProductIds();
        productIds.remove(Integer.valueOf(productId));
        kosik.setProductIds(productIds);
        kosikRepository.save(kosik);
        return kosikMapper.mapToDto(kosik);
    }

    public KosikDTO getKosik(int kosikId) {
        KosikEntity kosik = kosikRepository.findById(kosikId)
                .orElseThrow(() -> new RuntimeException("Kosik not found"));
        return kosikMapper.mapToDto(kosik);
    }
}
