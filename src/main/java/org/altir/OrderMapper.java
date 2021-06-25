package org.altir;

import org.altir.dto.ItemDto;
import org.altir.dto.OrderDto;
import org.altir.dto.WarehouseDto;
import org.altir.model.DeliveryInfo;
import org.altir.model.Item;
import org.altir.model.Order;
import org.altir.model.Warehouse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderDto mapOrderToDto(Order order) {
        if (order == null) return null;

        Item item = order.getItem();
        if (item == null) return null;

        DeliveryInfo deliveryInfo = order.getDeliveryInfo();
        if (deliveryInfo == null) return null;

        Warehouse warehouse = deliveryInfo.getWarehouse();
        if (warehouse == null) return null;

        ItemDto itemDto = mapItemToDto(item);
        WarehouseDto warehouseDto = mapWarehouseToDto(warehouse);

        return buildOrderDto(order.getId(), itemDto, warehouseDto, deliveryInfo);
    }

    private ItemDto mapItemToDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getName())
                .build();
    }

    private WarehouseDto mapWarehouseToDto(Warehouse warehouse) {
        return WarehouseDto.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .address(warehouse.getAddress())
                .build();
    }

    private OrderDto buildOrderDto(Long orderId, ItemDto itemDto, WarehouseDto warehouseDto, DeliveryInfo deliveryInfo) {
        return OrderDto.builder()
                .orderId(orderId)
                .itemInfo(itemDto)
                .warehouseInfo(warehouseDto)
                .deliveryTime(deliveryInfo.getDeliveryTime())
                .warehouseDistance(deliveryInfo.getWarehouseDistance())
                .build();
    }

}
