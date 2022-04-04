INSERT INTO t_permission (id, permission_value, permission_label)
VALUES ('viewrekening', 'VIEW_REKENING', 'Lihat Data Rekening'),
       ('viewmutasi', 'VIEW_MUTASI', 'Lihat Data Mutasi'),
       ('edittransfer', 'EDIT_TRANSFER', 'Input Transfer');

INSERT INTO t_role (id, description, name)
VALUES ('staff', 'STAFF', 'Staff'),
       ('manager', 'MANAGER', 'Manager');

INSERT INTO t_role_permission (id_role, id_permission)
VALUES ('staff', 'viewrekening'),
       ('staff', 'viewmutasi'),
       ('manager', 'viewrekening'),
       ('manager', 'viewmutasi'),
       ('manager', 'edittransfer');

INSERT INTO t_user (id, username, id_role)
VALUES ('u001', 'jvmdeveloperid@gmail.com', 'staff');

INSERT INTO t_user (id, username, id_role)
VALUES ('u002', 'hendisantika@gmail.com', 'manager'),
       ('u003', 'jvmindonesia@gmail.com', 'manager');
