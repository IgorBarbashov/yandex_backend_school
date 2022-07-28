package com.example.spring_boot_rest_api.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, String> {

    List<Good> findByIdIn(List<String> ids);
}
