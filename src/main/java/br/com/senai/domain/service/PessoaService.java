package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private PessoaAssembler pessoaAssembler;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa) {

//        boolean emailValidation = pessoaRepository.findByEmail(pessoa.getUsuario().getEmail())
//                .isPresent();
//
//        if (emailValidation) {
//            throw new NegocioException("Já existe uma pessoa com este e-mail cadastrado.");
//        }

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Long pessoaId) {
        pessoaRepository.deleteById(pessoaId);
    }

    public List<PessoaDTO> listar() {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    public ResponseEntity<PessoaDTO> buscar(Long pessoaId) {
        return pessoaRepository.findById(pessoaId).map
                (pessoa -> {
                    return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
                }).orElse(ResponseEntity.notFound().build());
    }

    public List<PessoaDTO> listarPorNome(String pessoaNome) {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNome(pessoaNome));
    }

    public List<PessoaDTO> listarNomeContaining(String nomeContaining) {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNomeContaining(nomeContaining));
    }

    public ResponseEntity<PessoaDTO> editar(Long pessoaId, PessoaInputDTO pessoaInputDTO) {

    if(!pessoaRepository.existsById(pessoaId))
    { return ResponseEntity.notFound().build(); }

    Pessoa pessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        pessoa.setId(pessoaId);
        pessoaRepository.save(pessoa);
    PessoaDTO pessoaDTO = pessoaAssembler.toModel(pessoa);

    return ResponseEntity.ok(pessoaDTO);
    }
}
