--liquibase formatted sql
--changeset adobrowolska:3


INSERT INTO role (id, name) VALUES(1, 'ADMIN');
INSERT INTO role (id, name) VALUES(2, 'USER');