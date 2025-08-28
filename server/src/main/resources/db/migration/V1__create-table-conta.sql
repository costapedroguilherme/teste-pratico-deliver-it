CREATE TABLE tbl_conta (
    conta_id INTEGER PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor_original NUMERIC(1000,2) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE NOT NULL
);