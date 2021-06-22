package org.altir.repository;

import org.altir.model.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Long> {

    @Query("FROM DeliveryInfo di WHERE di.client.id = :clientId AND di.warehouse.id IN(:warehouseIds)")
    List<DeliveryInfo> findDeliveryInfosByClientIdAndWarehouseIdsIn(Long clientId, List<Long> warehouseIds);

}
