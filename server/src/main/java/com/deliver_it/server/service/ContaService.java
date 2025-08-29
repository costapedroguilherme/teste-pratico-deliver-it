package com.deliver_it.server.service;

import com.deliver_it.server.model.Conta;
import com.deliver_it.server.repo.ContaRepository;
import org.springframework.stereotype.Service;

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
        contaRepository.save(conta);
    }
}
