package org.altir.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateOrderDto {

    private long clientId;

    private long itemId;

}
