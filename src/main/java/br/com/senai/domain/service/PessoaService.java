package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.api.model.input.PessoaInput;
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
    public Pessoa cadastrar(PessoaInput pessoaInput) {

        boolean emailValidation = pessoaRepository.findByEmail(pessoaInput.getEmail())
                .isPresent();

        if (emailValidation) {
            throw new NegocioException("Já existe uma pessoa com este e-mail cadastrado.");
        }
        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInput);
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Long pessoaId) {
        pessoaRepository.deleteById(pessoaId);
    }

    public List<PessoaModel> listar() {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    public ResponseEntity<PessoaModel> buscar(Long pessoaId) {
        return pessoaRepository.findById(pessoaId).map
                (pessoa -> {
                    return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
                }).orElse(ResponseEntity.notFound().build());
    }

    public List<PessoaModel> listarPorNome(String pessoaNome) {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNome(pessoaNome));
    }

    public List<PessoaModel> listarNomeContaining(String nomeContaining) {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNomeContaining(nomeContaining));
    }

    public ResponseEntity<PessoaModel> editar(Long pessoaId, PessoaInput pessoaInput) {

    if(!pessoaRepository.existsById(pessoaId))
    { return ResponseEntity.notFound().build(); }

    Pessoa pessoa = pessoaAssembler.toEntity(pessoaInput);
        pessoa.setId(pessoaId);
        pessoaRepository.save(pessoa);
    PessoaModel pessoaModel = pessoaAssembler.toModel(pessoa);

    return ResponseEntity.ok(pessoaModel);
    }
}
