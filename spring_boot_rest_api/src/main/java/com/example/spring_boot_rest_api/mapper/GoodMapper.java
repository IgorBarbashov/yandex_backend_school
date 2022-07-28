package com.example.spring_boot_rest_api.mapper;

import com.example.spring_boot_rest_api.goods.Good;
import com.example.spring_boot_rest_api.goods.GoodDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GoodMapper implements AbstractMapper<Good, GoodDto> {

    private ModelMapper modelMapper;

    private LocalDateTime dateTime;

    public GoodMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper
                .getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.modelMapper = mapper;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Good toEntity(GoodDto source) {
        return toEntity(source, Good.class);
    }

    @Override
    public Good toEntity(GoodDto source, Class<Good> destinationType) {
        Good good = modelMapper.map(source, destinationType);
        good.setParent(null);
        good.setDate(dateTime);
        return good;
    }

    public List<Good> toEntity(List<GoodDto> source) {
        return toEntity(source, Good.class);
    }

    @Override
    public List<Good> toEntity(List<GoodDto> source, Class<Good> destinationType) {
        return source
                .stream()
                .map(s -> toEntity(s, destinationType))
                .collect(Collectors.toList());
    }

    @Override
    public GoodDto toDto(Good source, Class<GoodDto> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public List<GoodDto> toDto(List<Good> source, Class<GoodDto> destinationType) {
        return source
                .stream()
                .map(s -> modelMapper.map(s, destinationType))
                .collect(Collectors.toList());
    }
}
