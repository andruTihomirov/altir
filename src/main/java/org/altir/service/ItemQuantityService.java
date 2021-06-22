package org.altir.service;

import lombok.RequiredArgsConstructor;
import org.altir.exception.NotFoundException;
import org.altir.model.ItemQuantityInfo;
import org.altir.repository.ItemQuantityInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemQuantityService {

    private final ItemQuantityInfoRepository itemQuantityInfoRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void findWarehouseWhereItemExistsAndDecrementQuantity(Long itemId, Long warehouseId) {
        ItemQuantityInfo itemQuantityInfo = itemQuantityInfoRepository.findByItemIdAndWarehouseId(itemId, warehouseId)
                .orElseThrow(() -> new NotFoundException("Item with id [{0}] not exist.", itemId));
        int quantity = itemQuantityInfo.getQuantity();
        itemQuantityInfo.setQuantity(--quantity);
        itemQuantityInfoRepository.save(itemQuantityInfo);
    }

}
