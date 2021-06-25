package org.altir.service;

import lombok.RequiredArgsConstructor;
import org.altir.OrderMapper;
import org.altir.dto.CreateOrderDto;
import org.altir.dto.OrderDto;
import org.altir.exception.NotFoundException;
import org.altir.exception.OrderException;
import org.altir.model.DeliveryInfo;
import org.altir.model.Item;
import org.altir.model.Order;
import org.altir.repository.DeliveryInfoRepository;
import org.altir.repository.ItemQuantityInfoRepository;
import org.altir.repository.ItemRepository;
import org.altir.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Long clientId = createOrderDto.getClientId();
        Long itemId = createOrderDto.getItemId();

        throwIfOrderAlreadyExists(itemId, clientId);

        Item item = findItemOrThrow(itemId);
        List<Long> warehouseIdsWhereItemExists = findWarehouseIdsWhereItemExistsOrThrow(itemId);
        List<DeliveryInfo> deliveryInfos = findDeliveryInfosAboutWarehousesForClient(clientId, warehouseIdsWhereItemExists);
        return tryToCreateOrder(item, deliveryInfos);
    }

    private void throwIfOrderAlreadyExists(Long itemId, Long clientId) {
        if (orderRepository.isExist(itemId, clientId)) {
            throw new OrderException("Order for item id: [{0}] and client id: [{1}] already exists.", itemId, clientId);
        }
    }

    private Item findItemOrThrow(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Item with id [{0}] not exists.", id));
    }

    private List<Long> findWarehouseIdsWhereItemExistsOrThrow(Long itemId) {
        List<Long> warehouseIds = itemQuantityInfoRepository.findWarehouseIdsWhereItemExists(itemId);
        if (warehouseIds.isEmpty()) {
            throw new NotFoundException("Item with id [{0}] is out of warehouses", itemId);
        }
        return warehouseIds;
    }

    private List<DeliveryInfo> findDeliveryInfosAboutWarehousesForClient(Long clientId, List<Long> warehouseIds) {
       return deliveryInfoRepository.findDeliveryInfosByClientIdAndWarehouseIdsIn(clientId, warehouseIds);
    }

    private Optional<OrderDto> tryToCreateOrder(Item item, List<DeliveryInfo> deliveryInfos) {
        long itemId = item.getId();
        for (DeliveryInfo deliveryInfo : deliveryInfos) {
            Long warehouseId = deliveryInfo.getWarehouse().getId();
            boolean isDecremented = itemQuantityService.decrementItemQuantity(itemId, warehouseId);
            if (isDecremented) {
                Order savedOrder = createAndSaveOrder(item, deliveryInfo);
                return Optional.of(mapper.mapOrderToDto(savedOrder));
            }
        }
        throw new NotFoundException("Item with id [{0}] is out of warehouses", itemId);
    }

    private Order createAndSaveOrder(Item item, DeliveryInfo deliveryInfo) {
        Order order = new Order();
        order.setItem(item);
        order.setDeliveryInfo(deliveryInfo);
        return orderRepository.save(order);
    }

}
