package org.altir.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "items_quantity_infos")
public class ItemQuantityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Warehouse warehouse;

    private int quantity;

}
