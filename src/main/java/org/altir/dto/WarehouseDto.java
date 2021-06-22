package org.altir.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WarehouseDto {

    private long id;

    private String name;

    private String address;

}
