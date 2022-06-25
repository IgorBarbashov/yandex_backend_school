// Service (interface)
// ServiceImpl

package com.example.spring_boot_rest_api.goods;

import com.example.spring_boot_rest_api.mapper.GoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodService {

    private final static Logger logger = LoggerFactory.getLogger(GoodController.class);

    private final GoodRepository goodRepository;

    private final GoodMapper goodMapper;

    @Autowired
    public GoodService(GoodRepository goodRepository, GoodMapper goodMapper) {
        this.goodRepository = goodRepository;
        this.goodMapper = goodMapper;
    }

    public void add(Good good) {
        goodRepository.save(good);
    }

    public void add(GoodListDto goodListDto) {
        List<GoodDto> goodsDto = goodListDto.getItems();
        LocalDateTime updateDate = goodListDto.getUpdateDate();
        List<String> goodIdsDto = goodsDto.stream().map(GoodDto::getId).collect(Collectors.toList());
        List<String> goodParentIdsDto = goodsDto.stream().map(GoodDto::getParentId).collect(Collectors.toList());

        // validation-level-0:
        // - validate incoming date format on the Application level (mvcConversionService)
        // - bean validation (standard annotations, custom constraint annotation)

        // validation-level-1: validate input data for consistence

        // validation-level-2: map DTO to Entity
        goodMapper.setDateTime(updateDate);
        List<Good> goods = goodMapper.toEntity(goodsDto);

        // validation-level-2: read necessary data from DB
        // validation-level-2: validate consistence of the incoming and DB data

        // prepare data for save to DB

        goodRepository.saveAll(goods);
    }

    public List<Good> list() {
        return goodRepository.findAll();
    }
}
