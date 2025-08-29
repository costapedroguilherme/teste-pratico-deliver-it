package com.deliver_it.server.service;

import com.deliver_it.server.model.Conta;
import com.deliver_it.server.repo.ContaRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> getAllContas() {
        return contaRepository.findAll();
    }

    public void insertConta(Conta conta) {
        conta.setValorCorrigido(conta.getValorOriginal());
        if (conta.getDataPagamento() != null) {
            conta.setQuitado(true);
            long diasAtraso =
                    getDiasAtraso(conta.getDataVencimento(), conta.getDataPagamento());
            if (diasAtraso >= 1) {
                conta.setValorCorrigido(
                        Conta.calcularValorCorrigido(conta.getValorOriginal(), diasAtraso)
                );
                conta.setQtdDiasAtraso((int) diasAtraso);
            }
        } else {
            long diasAtraso =
                    getDiasAtraso(conta.getDataVencimento(), LocalDate.now());
            if (diasAtraso >= 1) {
                conta.setValorCorrigido(
                        Conta.calcularValorCorrigido(conta.getValorOriginal(), diasAtraso)
                );
                conta.setQtdDiasAtraso((int) diasAtraso);
            }
        }
        contaRepository.save(conta);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void appListener() throws InterruptedException {
        updateValorCorrigido();
    }

    @Scheduled(cron = "00 00 00 * * *", zone = "America/Sao_Paulo")
    private void cron() {
        updateValorCorrigido();
    }

    private void updateValorCorrigido() {
        for (Conta conta : contaRepository.findAll()) {
            if (conta.getQuitado()) {
                continue;
            }
            long diasAtraso = getDiasAtraso(conta.getDataVencimento(), LocalDate.now());
            if (diasAtraso >= 1) {
                conta.setValorCorrigido(Conta.calcularValorCorrigido(conta.getValorOriginal(), diasAtraso));
                contaRepository.save(conta);
            }
        }
    }

    private long getDiasAtraso(LocalDate dataVencimento, LocalDate dataAtraso) {
        return ChronoUnit.DAYS.between(dataVencimento, dataAtraso);
    }
}
