ALTER TABLE pessoa ADD COLUMN usuario_id bigint NOT NULL;

alter TABLE pessoa ADD CONSTRAINT fk_usuario_pessoa FOREIGN KEY (usuario_id) REFERENCES usuario(id);