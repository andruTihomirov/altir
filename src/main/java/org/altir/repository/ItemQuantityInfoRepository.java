package org.altir.repository;

import org.altir.model.ItemQuantityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemQuantityInfoRepository extends JpaRepository<ItemQuantityInfo, Long> {

    @Query(value = "SELECT * FROM items_quantity_infos iqi WHERE iqi.item_id = :itemId AND iqi.warehouse_id = :warehouseId AND iqi.quantity > 0 FOR UPDATE",
            nativeQuery = true)
    Optional<ItemQuantityInfo> findByItemIdAndWarehouseId(Long itemId, Long warehouseId);

    @Query("SELECT iqi.warehouse.id FROM ItemQuantityInfo iqi WHERE iqi.item.id = :itemId AND iqi.quantity > 0")
    List<Long> findWarehouseIdsWhereItemExists(Long itemId);

}
