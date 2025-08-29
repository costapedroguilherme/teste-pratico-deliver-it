package com.deliver_it.server.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conta_id")
    private Integer contaId;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "valor_original", nullable = false)
    private BigDecimal valorOriginal;

    @Column(name = "valor_corrigido", nullable = true)
    private BigDecimal valorCorrigido;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "qtd_dias_atraso")
    private int qtdDiasAtraso;

    @Column(name = "quitado")
    private boolean quitado;

    public Conta(){}

    public Conta(
            Integer contaId, String nome, BigDecimal valorOriginal,
            BigDecimal valorCorrigido, LocalDate dataVencimento,
            LocalDate dataPagamento, int qtdDiasAtraso,
            boolean quitado) {
        this.contaId = contaId;
        this.nome = nome;
        this.valorOriginal = valorOriginal;
        this.valorCorrigido = valorCorrigido;
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
        this.qtdDiasAtraso = qtdDiasAtraso;
        this.quitado = quitado;
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorCorrigido() {
        return valorCorrigido;
    }

    public void setValorCorrigido(BigDecimal valorCorrigido) {
        this.valorCorrigido = valorCorrigido;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getQtdDiasAtraso() {
        return qtdDiasAtraso;
    }

    public void setQtdDiasAtraso(int qtdDiasAtraso) {
        this.qtdDiasAtraso = qtdDiasAtraso;
    }

    public boolean getQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }

    public static BigDecimal calcularValorCorrigido(BigDecimal valorOriginal, long diasAtraso) {
        if (diasAtraso <= 3) {
            return valorOriginal.add(calcularValorPorcentagem
                    (valorOriginal, 2, 0.1 * diasAtraso))
                    .setScale(2, RoundingMode.HALF_UP);
        } else if (diasAtraso <= 5) {
            return valorOriginal.add(calcularValorPorcentagem
                    (valorOriginal, 3, 0.2 * diasAtraso))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return valorOriginal.add(calcularValorPorcentagem
                (valorOriginal, 5, 0.3 * diasAtraso))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal calcularValorPorcentagem(BigDecimal valorOriginal, int multa, double juros) {
        return valorOriginal.multiply(BigDecimal.valueOf((double)multa/100)).add(valorOriginal.multiply(BigDecimal.valueOf(juros/100)));
    }
}
