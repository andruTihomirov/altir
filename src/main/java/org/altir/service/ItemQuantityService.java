package org.altir.service;

import lombok.RequiredArgsConstructor;
import org.altir.model.ItemQuantityInfo;
import org.altir.repository.ItemQuantityInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemQuantityService {

    private final ItemQuantityInfoRepository itemQuantityInfoRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean decrementItemQuantity(Long itemId, Long warehouseId) {
        Optional<ItemQuantityInfo> itemQuantityInfo = itemQuantityInfoRepository.findByItemIdAndWarehouseId(itemId, warehouseId);

        if (itemQuantityInfo.isEmpty()) {
            return false;
        }

        int quantity = itemQuantityInfo.get().getQuantity();

        itemQuantityInfo.get().setQuantity(--quantity);
        itemQuantityInfoRepository.save(itemQuantityInfo.get());
        return true;
    }

}
