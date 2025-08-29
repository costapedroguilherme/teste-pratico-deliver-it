CREATE TABLE tbl_conta (
    conta_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor_original NUMERIC(1000,2) NOT NULL,
    valor_corrigido NUMERIC(1000,2),
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    quitado BOOLEAN DEFAULT FALSE
);