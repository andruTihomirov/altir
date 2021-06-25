package org.altir.repository;

import org.altir.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT count(*) > 0 FROM orders o " +
            "JOIN delivery_infos di ON o.delivery_info_id = di.id " +
            "WHERE o.item_id = :itemId " +
            "AND di.client_id = :clientId",
            nativeQuery = true)
    boolean isExist(Long itemId, Long clientId);

}
