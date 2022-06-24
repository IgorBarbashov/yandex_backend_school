// Service (interface)
// ServiceImpl

package com.example.spring_boot_rest_api.goods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodService {

    private final static Logger logger = LoggerFactory.getLogger(GoodController.class);

    private final GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    public void add(Good good) {
        goodRepository.save(good);
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    @Transactional
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void add(GoodListDto goodListDto) {
        List<GoodDto> goodsDto = goodListDto.getItems();
        LocalDateTime updateDate = goodListDto.getUpdateDate();
        List<String> goodIdsDto = goodsDto.stream().map(GoodDto::getId).collect(Collectors.toList());
        List<String> goodParentIdsDto = goodsDto.stream().map(GoodDto::getParentId).collect(Collectors.toList());

//        logger.info("goodsDto: " + goodsDto);
//        logger.info("updateDate: " + updateDate);
//        logger.info("goodIdsDto: " + goodIdsDto);
//        logger.info("goodParentIdsDto: " + goodParentIdsDto);

        // validation-level-0:
        // - validate incoming date format on the Application level (mvcConversionService)
        // - bean validation (standard annotations, custom constraint annotation)

        // validation-level-1: validate input data for consistence

        // validation-level-2: map DTO to Entity
        Good good = new Good(
            goodsDto.get(0).getId(),
            goodsDto.get(0).getName(),
            goodsDto.get(0).getAmount(),
            goodsDto.get(0).getType(),
            goodsDto.get(0).getPrice(),
            goodsDto.get(0).getParentId()
        );

        // validation-level-2: read necessary data from DB
        List<Good> parentsDb = goodRepository.findByIdIn(goodParentIdsDto);

        parentsDb.get(0).addChild(good);

        // validation-level-2: validate consistence of the incoming and DB data



        // prepare data for save to DB
        // - add to all incoming entities updateDate field
//        for (Good good : goods) {
//            good.setUpdateDate(updateDate);
//        }

        goodRepository.saveAll(parentsDb);
    }
}
