package com.example.spring_boot_rest_api.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    private final GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    public void add(Good good) {
        goodRepository.save(good);
    }

    public void add(List<Good> goods) {
        goodRepository.saveAll(goods);
    }
}
