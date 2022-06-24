package com.example.spring_boot_rest_api.goods;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class GoodListDto {

    @NotEmpty
    private List<GoodDto> items;

    @NotNull
    @DateTimeFormat
    private LocalDateTime updateDate;

    public GoodListDto() {
    }

    public GoodListDto(List<GoodDto> items, LocalDateTime updateDate) {
        this.items = items;
        this.updateDate = updateDate;
    }

    public List<GoodDto> getItems() {
        return items;
    }

    public void setItems(List<GoodDto> items) {
        this.items = items;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "GoodListDTO{" +
                "items=" + items +
                ", updateDate=" + updateDate +
                '}';
    }
}
