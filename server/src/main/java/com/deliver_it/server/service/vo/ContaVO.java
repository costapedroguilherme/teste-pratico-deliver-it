package com.deliver_it.server.service.vo;

import com.deliver_it.server.exception.ContaMissingDataException;
import io.micrometer.common.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaVO {
    private final String nome;
    private final BigDecimal valorOriginal;
    private final LocalDate dataVencimento;

    public ContaVO(
            String nome, BigDecimal valorOriginal,
            LocalDate dataVencimento) {
        this.nome = nome;
        this.valorOriginal = valorOriginal;
        this.dataVencimento = dataVencimento;
    }

    public void validate() throws ContaMissingDataException {
        if (StringUtils.isBlank(nome)) {
            throw new ContaMissingDataException("Favor informar um nome para a conta");
        }
        if (valorOriginal == null) {
            throw new ContaMissingDataException("Favor informar o valor original da conta");
        }
        if (dataVencimento == null) {
            throw new ContaMissingDataException("Favor informar a data de vencimento da conta");
        }
    }
}
