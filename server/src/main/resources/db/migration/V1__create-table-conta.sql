CREATE TABLE tbl_conta (
    conta_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor_original NUMERIC(1000,2) NOT NULL,
    valor_corrigido NUMERIC(1000,2),
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    qtd_dias_atraso INT DEFAULT 0,
    quitado BOOLEAN DEFAULT FALSE
);