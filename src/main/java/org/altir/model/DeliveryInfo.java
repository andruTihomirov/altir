package org.altir.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "delivery_infos")
public class DeliveryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Client client;

    @OneToOne
    private Warehouse warehouse;

    private int warehouseDistance;

    private int deliveryTime;

}
