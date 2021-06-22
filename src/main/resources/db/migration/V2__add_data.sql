INSERT INTO clients(id, name) VALUES(1, 'Client Name 1');
INSERT INTO clients(id, name) VALUES(2, 'Client Name 2');
INSERT INTO clients(id, name) VALUES(3, 'Client Name 3');

INSERT INTO items(id, name, description) VALUES(1, 'Item Name 1', 'Item Description 1');
INSERT INTO items(id, name, description) VALUES(2, 'Item Name 2', 'Item Description 2');
INSERT INTO items(id, name, description) VALUES(3, 'Item Name 3', 'Item Description 3');

INSERT INTO warehouses(id, name, address) VALUES(1, 'Warehouse Name 1', 'Warehouse Address 1');
INSERT INTO warehouses(id, name, address) VALUES(2, 'Warehouse Name 2', 'Warehouse Address 2');
INSERT INTO warehouses(id, name, address) VALUES(3, 'Warehouse Name 3', 'Warehouse Address 3');

INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(1, 3, 1, 1);
INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(2, 3, 1, 2);
INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(3, 1, 1, 3);

INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(4, 10, 2, 1);
INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(5, 15, 2, 2);
INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(6, 17, 2, 3);

INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(7, 0, 3, 1);
INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(8, 5, 3, 2);
INSERT INTO items_quantity_infos(id, quantity, item_id, warehouse_id) VALUES(9, 1, 3, 3);

INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(1, 10, 100, 1, 1);
INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(2, 50, 500, 1, 2);
INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(3, 60, 600, 1, 3);

INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(4, 100, 1000, 2, 1);
INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(5, 500, 5000, 2, 2);
INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(6, 600, 6000, 2, 3);

INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(7, 10, 100, 3, 1);
INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(8, 50, 500, 3, 2);
INSERT INTO delivery_infos(id, delivery_time, warehouse_distance, client_id, warehouse_id) VALUES(9, 60, 600, 3, 3);