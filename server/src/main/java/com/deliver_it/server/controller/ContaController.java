package com.deliver_it.server.controller;

import com.deliver_it.server.dto.ContaResponse;
import com.deliver_it.server.dto.ErrorMessage;
import com.deliver_it.server.dto.SuccessMessage;
import com.deliver_it.server.exception.ContaMissingDataException;
import com.deliver_it.server.exception.ContaNotFoundException;
import com.deliver_it.server.model.Conta;
import com.deliver_it.server.service.ContaService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addNewConta(@RequestBody Conta conta) {
        try {
            return ResponseEntity.ok(new ContaResponse(contaService.insertConta(conta)));
        } catch (ContaMissingDataException e) {
            return ResponseEntity.status(400).body(new ErrorMessage(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage("Falha desconhecida!"));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteConta(@PathVariable Integer id) {
        try {
            contaService.deleteConta(id);
            return ResponseEntity.ok(new SuccessMessage("Conta excluída com sucesso!"));
        } catch (ContaNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage("Falha desconhecida!"));
        }
    }

    @PutMapping("/quitado/{id}")
    public ResponseEntity<?> updateConta(@PathVariable Integer id) {
        try {
            contaService.updateConta(id);
            return ResponseEntity.ok(new SuccessMessage("Conta atualizada com sucesso!"));
        } catch (ContaNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorMessage("Falha desconhecida!"));
        }
    }
}
