package com.deliver_it.server.repo;

import com.deliver_it.server.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
}
