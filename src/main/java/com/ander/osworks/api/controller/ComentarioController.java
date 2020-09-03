package com.ander.osworks.api.controller;


import com.ander.osworks.api.model.Comentario;
import com.ander.osworks.api.model.ComentarioInput;
import com.ander.osworks.api.model.ComentarioModel;
import com.ander.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.ander.osworks.domain.model.OrdemServico;
import com.ander.osworks.domain.repository.OrdemServicoRepository;
import com.ander.osworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    GestaoOrdemServicoService gestaoOrdemServico;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

        return toCollectionModel(ordemServico.getComentarios());

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId,
				comentarioInput.getDescricao());

		return toModel(comentario);
	}

    private ComentarioModel toModel (Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    private List<ComentarioModel> toCollectionModel (List<Comentario> comentarios) {
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}