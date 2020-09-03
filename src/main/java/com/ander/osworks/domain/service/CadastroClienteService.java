package com.ander.osworks.domain.service;

import com.ander.osworks.domain.exception.NegocioException;
import com.ander.osworks.domain.model.Cliente;
import com.ander.osworks.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistente != null && !clienteExistente.equals((cliente))) {
            throw new NegocioException("Já existe um cliente cadastro com esse endereço de e-mail.");

        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
