package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomerDTO;
import com.example.bookingmodel.data.entity.CustomerEntity;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomerMapper {

    private final ModelMapper modelMapper;

    public CustomerEntity mapToEntity(CustomerDTO dto) {
        return modelMapper.map(dto, CustomerEntity.class).toBuilder().build();
    }

    public CustomerDTO mapToDto(CustomerEntity entity) {
        return modelMapper.map(entity, CustomerDTO.class).toBuilder().build();
    }
}
