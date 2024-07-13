--liquibase formatted sql
--changeset adobrowolska:4

INSERT INTO user(id, first_name, last_name, email, password)
VALUES(6, 'Admin', 'Admin', 'admin@ff.com', '$2a$12$fAv80CKPFqu8.CZzp6fpyuZf4EVACKOs0VzTaZ/O5ptdV2GqG/5b2');

INSERT INTO user_role(id, role_id, user_id) VALUES(6, 1, 6);
