package com.ander.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ander.osworks.domain.model.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByNome (String nome);
    List<Cliente> findByNomeContaining (String nome);
    Cliente findByEmail(String email);
}
