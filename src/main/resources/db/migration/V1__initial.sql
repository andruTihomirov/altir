CREATE TABLE clients(
    id bigserial PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE warehouses(
    id bigserial PRIMARY KEY,
    address VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE items(
    id bigserial PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE delivery_infos(
    id bigserial PRIMARY KEY,
    delivery_time INTEGER NOT NULL,
    warehouse_distance INTEGER NOT NULL,
    client_id bigint CONSTRAINT fk_client_id REFERENCES clients(id),
    warehouse_id bigint CONSTRAINT fk_warehouse_id REFERENCES warehouses(id)
);

CREATE TABLE orders(
    id bigserial PRIMARY KEY,
    delivery_info_id bigint CONSTRAINT fk_delivery_info_id REFERENCES delivery_infos(id),
    item_id bigint CONSTRAINT fk_item_id REFERENCES items(id)
);

CREATE TABLE items_quantity_infos(
    id bigserial PRIMARY KEY,
    quantity INTEGER,
    item_id bigint CONSTRAINT fk_item_id REFERENCES items(id),
    warehouse_id  bigint CONSTRAINT fk_warehouse_id REFERENCES warehouses(id)
);
