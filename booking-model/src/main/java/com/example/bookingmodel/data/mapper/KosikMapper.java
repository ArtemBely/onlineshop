package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.KosikDTO;
import com.example.bookingmodel.data.entity.KosikEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class KosikMapper {


    private final ModelMapper modelMapper;

    public KosikDTO mapToDto(KosikEntity entity) {
        return modelMapper.map(entity, KosikDTO.class).toBuilder().build();
    }

    public KosikEntity mapToEntity(KosikDTO dto) {
        return modelMapper.map(dto, KosikEntity.class).toBuilder().build();
    }
}
