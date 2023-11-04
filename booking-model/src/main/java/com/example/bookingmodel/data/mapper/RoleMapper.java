package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomerDTO;
import com.example.bookingmodel.data.dto.RoleDTO;
import com.example.bookingmodel.data.entity.CustomerEntity;
import com.example.bookingmodel.data.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleEntity mapToEntity(RoleDTO dto) {
        return modelMapper.map(dto, RoleEntity.class).toBuilder().build();
    }

    public RoleDTO mapToDto(RoleEntity entity) {
        return modelMapper.map(entity, RoleDTO.class).toBuilder().build();
    }
}
