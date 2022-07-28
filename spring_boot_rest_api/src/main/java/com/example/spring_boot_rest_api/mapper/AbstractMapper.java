package com.example.spring_boot_rest_api.mapper;

import java.util.List;

public interface AbstractMapper<E, D> {

    E toEntity(D source, Class<E> destinationType);

    List<E> toEntity(List<D> source, Class<E> destinationType);

    D toDto(E source, Class<D> destinationType);

    List<D> toDto(List<E> source, Class<D> destinationType);

}
