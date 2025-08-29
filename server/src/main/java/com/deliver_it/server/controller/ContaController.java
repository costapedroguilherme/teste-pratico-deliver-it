package com.deliver_it.server.controller;

import com.deliver_it.server.model.Conta;
import com.deliver_it.server.service.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<Conta> getConta() {
        return contaService.getAllContas();
    }

    @PostMapping
    public void addNewConta(@RequestBody Conta conta) {
        contaService.insertConta(conta);
    }

    @DeleteMapping("{id}")
    public void deleteConta(@PathVariable Integer id) {
        contaService.deleteConta(id);
    }
}
