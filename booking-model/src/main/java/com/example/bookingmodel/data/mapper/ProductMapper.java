package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.ProductDTO;
import com.example.bookingmodel.data.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductEntity mapToEntity(ProductDTO dto) {
        return modelMapper.map(dto, ProductEntity.class).toBuilder().build();
    }

    public ProductDTO mapToDto(ProductEntity entity) {
        return modelMapper.map(entity, ProductDTO.class).toBuilder().build();
    }
}
