//package br.com.senai.domain.model;
//
//import br.com.senai.domain.ValidationGroups;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.sun.istack.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.Valid;
//import javax.validation.groups.ConvertGroup;
//import javax.validation.groups.Default;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "entregas")
//public class Entrega {
//
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Valid
//	@ConvertGroup(from = Default.class, to = ValidationGroups.clienteID.class)
//	@NotNull
//	@ManyToOne
//	@JoinColumn(name = "pessoa_id")
//	private Pessoa pessoa;
//
//	@Valid
//	@Embedded
//	private Destinatario destinatario;
//
//	@Valid
//	@NotNull
//	private BigDecimal taxa;
//
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
//	@Enumerated(EnumType.STRING)
//	private StatusEntrega status;
//
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
//	private LocalDateTime dataPedido;
//
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
//	private LocalDateTime dataFinalizacao;
//}
package br.com.senai.domain.model;

		import br.com.senai.api.model.PessoaModel;
		import br.com.senai.domain.ValidationGroups;
		import br.com.senai.domain.exception.NegocioException;
		import com.fasterxml.jackson.annotation.JsonProperty;
		import lombok.Getter;
		import lombok.Setter;

		import javax.persistence.*;
		import javax.validation.Valid;
		import javax.validation.constraints.NotNull;
		import javax.validation.groups.ConvertGroup;
		import javax.validation.groups.Default;
		import java.math.BigDecimal;
		import java.time.LocalDateTime;
		import java.util.ArrayList;
		import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "entregas")
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.clienteID.class)
	@NotNull
	@ManyToOne
	private Pessoa pessoa;

	@Valid
	@NotNull
	@Embedded
	private Destinatario destinatario;

	@Valid
	@NotNull
	private BigDecimal taxa;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataPedido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;

	public void finalizar() {
		if(naoPodeFinalizar()){
			throw new NegocioException("Entrega não pode ser finalizada.");
		}

		setStatus(StatusEntrega.FINALIZADA);
		setDataFinalizacao(LocalDateTime.now());
	}

	public boolean podeFinalizar() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}
	public boolean naoPodeFinalizar() {
		return !podeFinalizar();
	}

	public Ocorrencia adicionarOcorrencia(String descricao){
		Ocorrencia ocorrencia = new Ocorrencia();

		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(LocalDateTime.now());
		ocorrencia.setEntrega(this);
		this.getOcorrencias().add(ocorrencia);
		return ocorrencia;
	}

}