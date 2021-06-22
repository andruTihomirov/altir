package org.altir.service;

import lombok.RequiredArgsConstructor;
import org.altir.OrderMapper;
import org.altir.dto.CreateOrderDto;
import org.altir.dto.OrderDto;
import org.altir.model.DeliveryInfo;
import org.altir.model.Item;
import org.altir.model.Order;
import org.altir.repository.DeliveryInfoRepository;
import org.altir.repository.ItemQuantityInfoRepository;
import org.altir.repository.ItemRepository;
import org.altir.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemQuantityService itemQuantityService;

    private final ItemRepository itemRepository;

    private final OrderRepository orderRepository;

    private final ItemQuantityInfoRepository itemQuantityInfoRepository;

    private final DeliveryInfoRepository deliveryInfoRepository;

    private final OrderMapper mapper;


    public Optional<OrderDto> get(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(mapper::mapOrderToDto);
    }

    @Transactional
    public Optional<OrderDto> create(CreateOrderDto createOrderDto) {
        Item item = itemRepository.findById(createOrderDto.getItemId()).orElseThrow();

        List<Long> warehouseIds = itemQuantityInfoRepository.findWarehouseIdsWhereItemExists(item.getId());

        DeliveryInfo deliveryInfo = findDeliveryInfoWithNearestWarehouse(createOrderDto.getClientId(), warehouseIds);

        itemQuantityService.findWarehouseWhereItemExistsAndDecrementQuantity(item.getId(), deliveryInfo.getWarehouse()
                .getId());

        Order savedOrder = createAndSaveOrder(item, deliveryInfo);
        return Optional.of(mapper.mapOrderToDto(savedOrder));
    }

    private DeliveryInfo findDeliveryInfoWithNearestWarehouse(Long clientId, List<Long> warehouseIds) {
        return deliveryInfoRepository
                .findDeliveryInfosByClientIdAndWarehouseIdsIn(clientId, warehouseIds)
                .stream()
                .min(Comparator.comparing(DeliveryInfo::getDeliveryTime))
                .orElseThrow();
    }

    private Order createAndSaveOrder(Item item, DeliveryInfo deliveryInfo) {
        Order order = new Order();
        order.setItem(item);
        order.setDeliveryInfo(deliveryInfo);
        return orderRepository.save(order);
    }


}
