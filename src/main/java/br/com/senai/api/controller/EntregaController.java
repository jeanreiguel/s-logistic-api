package br.com.senai.api.controller;

import br.com.senai.api.assembler.EntregaAssembler;
import br.com.senai.api.input.EntregaInputDTO;
import br.com.senai.api.model.EntregaDTO;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.service.EntregaService;
import br.com.senai.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	private EntregaService entregaService;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInputDTO entregaInputDTO){
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInputDTO);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		return entregaAssembler.toModel(entregaSolicitada);
	}

	@GetMapping
	public List<EntregaDTO> listar() {
		return solicitacaoEntregaService.listar();
	}


	@GetMapping("/{entregaID}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaID) {
		return solicitacaoEntregaService.buscar(entregaID);
	}

	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		entregaService.finalizar(entregaId);
	}


}
