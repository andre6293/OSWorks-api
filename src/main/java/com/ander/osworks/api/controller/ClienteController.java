package com.ander.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ander.osworks.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ander.osworks.domain.model.Cliente;
import com.ander.osworks.domain.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
    private CadastroClienteService cadastroCliente;

    @GetMapping("/clientes")
    public List<Cliente> listar() {
    	return clienteRepository.findAll();
    }
    
    @GetMapping("/clientes/{clientId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clientId) {
    	Optional<Cliente> cliente = clienteRepository.findById(clientId);

    	if(cliente.isPresent()) {
    	    return ResponseEntity.ok(cliente.get());
        }
    	return ResponseEntity.notFound().build();
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return cadastroCliente.salvar(cliente);
    }

    @PutMapping("/clientes/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
                                             @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = cadastroCliente.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }


    @DeleteMapping("/clientes/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cadastroCliente.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }
}
