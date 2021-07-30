create table role_usuarios(
    usuarios_id bigint not null,
    role_nome_role varchar(45) not null
);

ALTER TABLE role_usuarios ADD CONSTRAINT fk_usuarios
FOREIGN KEY(usuarios_id) REFERENCES usuario(id);
ALTER TABLE role_usuarios ADD CONSTRAINT fk_role
FOREIGN KEY(role_nome_role) REFERENCES role(nome_role);

INSERT INTO usuario VALUES (null,"jean@gmail.com","a");

INSERT INTO ROLE_USUARIOS VALUES(1, "ROLE_ADMIN");