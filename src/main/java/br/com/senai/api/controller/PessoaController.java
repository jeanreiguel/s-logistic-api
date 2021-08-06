package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.assembler.UsuarioAssembler;
import br.com.senai.api.input.UsuarioInputDTO;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.input.PessoaInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.Usuario;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;
    private UsuarioAssembler usuarioAssembler;
    @GetMapping
    public List<PessoaDTO> listar(){ return pessoaService.listar();
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaDTO> listarPorNome(@PathVariable String pessoaNome){
        return pessoaService.listarPorNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<PessoaDTO> listarNomeContaining(@PathVariable String nomeContaining){
        return pessoaService.listarNomeContaining(nomeContaining);
    }

    @GetMapping("{pessoaId}")
    public ResponseEntity<PessoaDTO> buscar(@PathVariable Long pessoaId){
        return pessoaService.buscar(pessoaId);
    }

    @PostMapping
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa newPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        newPessoa.getUsuario().setSenha(new BCryptPasswordEncoder().encode(pessoaInputDTO.getUsuario().getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(newPessoa);
        return pessoaAssembler.toModel(pessoa);
    }
    @PostMapping("/usuario")
    public PessoaDTO cadUsuario(@Valid @RequestBody UsuarioInputDTO usuarioInputDTO){
        Usuario usuario = usuarioAssembler.toEntity(usuarioInputDTO);
        Pessoa newPessoa = new Pessoa();
        newPessoa.setUsuario(usuario);
        newPessoa.setTelefone("(47)98498-7259");
        newPessoa.setNome("Jorgeobom");

        newPessoa.getUsuario().setSenha(new BCryptPasswordEncoder().encode(usuarioInputDTO.getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(newPessoa);
        return pessoaAssembler.toModel(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long pessoaId,
                                            @RequestBody PessoaInputDTO pessoaInputDTO){
        return pessoaService.editar(pessoaId, pessoaInputDTO);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        pessoaService.excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }
}
