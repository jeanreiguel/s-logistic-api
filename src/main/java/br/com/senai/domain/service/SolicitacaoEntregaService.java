package br.com.senai.domain.service;

import br.com.senai.api.assembler.EntregaAssembler;
import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.DestinatarioModel;
import br.com.senai.api.model.EntregaModel;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.api.model.input.PessoaInput;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.StatusEntrega;
import br.com.senai.domain.repository.EntregaRepository;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private PessoaService pessoaService;
	private EntregaRepository entregaRepository;
	private EntregaAssembler entregaAssembler;
	private PessoaAssembler pessoaAssembler;
	private PessoaRepository pessoaRepository;

	@Transactional
	public Entrega solicitar(Entrega entrega){
		PessoaModel pessoa = pessoaService.buscar(entrega.getPessoa().getId()).getBody();

		entrega.setPessoa(pessoaAssembler.toEntityFromModel(pessoa));
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());

		return entregaRepository.save(entrega);
	}
	public List<EntregaModel> listar() {
			return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}

	public ResponseEntity<EntregaModel> buscar(Long entregaID) {
		return entregaRepository.findById(entregaID).map
				(entrega -> {
					return ResponseEntity.ok(entregaAssembler.toModel(entrega));
				}).orElse(ResponseEntity.notFound().build());
	}
}
