package com.deliver_it.server.dto;

import com.deliver_it.server.model.Conta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaResponse(Conta conta) {
}
