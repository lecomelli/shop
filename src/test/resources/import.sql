INSERT INTO product(id, sku, name, price) VALUES (1, 'ipd', 'Super iPad', 549.99 );
INSERT INTO product(id, sku, name, price) VALUES (2, 'mbp', 'MacBook Pro', 1399.99 );
INSERT INTO product(id, sku, name, price) VALUES (3, 'atv', 'Apple TV', 109.50 );
INSERT INTO product(id, sku, name, price) VALUES (4, 'vga', 'VGA adapter', 30.00);


INSERT INTO by_x_pay_for_y(id, name, active, product_id, discount_type, by_x, pay_for_y) VALUES (1,'BY_X_PAY_FOR_Y',true,3,'BY_X_PAY_FOR_Y', 3, 2 );

INSERT INTO bulk(id, name, active, product_id, discount_type, minimum_quantity, new_price) VALUES (2,'BULK',true,1,'BULK',4,499.99);

INSERT INTO by_x_of_a_get_y_of_b(id, name, active, product_id, discount_type, by_x, get_y, product_b_id ) VALUES (3,'BY_X_OF_A_GET_Y_OF_B',true,2,'BY_X_OF_A_GET_Y_OF_B',1,1,4);


