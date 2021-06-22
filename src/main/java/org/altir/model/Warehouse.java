package org.altir.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String address;

    @OneToMany
    @JoinColumn(name = "warehouse_id")
    private List<ItemQuantityInfo> itemQuantityInfos;

}
