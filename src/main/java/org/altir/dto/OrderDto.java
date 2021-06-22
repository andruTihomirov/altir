package org.altir.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDto {

    private ItemDto itemInfo;

    private WarehouseDto warehouseInfo;

    private int warehouseDistance;

    private int deliveryTime;
}
