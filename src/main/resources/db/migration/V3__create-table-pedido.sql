CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    cliente_id BIGINT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    valor_total NUMERIC(12,2),

    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);