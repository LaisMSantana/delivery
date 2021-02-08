package br.com.spring.delivery.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo nome é obrigatório")
	private String nome;

	@NotNull(message = "O campo CPF é obrigatório")
	@CPF(message = "CPF inválido")
	@Column(unique = true)
	private String cpf;

	@Size(min = 10)
	private String telefone;

	@NotNull(message = "O endereço é obrigatório")
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@NotEmpty(message = "O email é obrigatório")
	@Column(unique = true)
	private String email;

	@NotNull(message = "O tipo de pagamento é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoDePagamento tipoDePagamento;

	public Cliente() {
	}

	public Cliente(Long id, @NotBlank(message = "O campo nome é obrigatório") String nome,
			@NotNull(message = "O campo CPF é obrigatório") @CPF(message = "CPF inválido") String cpf,
			@Size(min = 10) String telefone, @NotNull(message = "O endereço é obrigatório") @Valid Endereco endereco,
			@NotEmpty(message = "O email é obrigatório") String email,
			@NotNull(message = "O tipo de pagamento é obrigatório") TipoDePagamento tipoDePagamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.tipoDePagamento = tipoDePagamento;
	}

	public Cliente(@NotBlank(message = "O campo nome é obrigatório") String nome,
			@NotNull(message = "O campo CPF é obrigatório") @CPF(message = "CPF inválido") String cpf,
			@Size(min = 10) String telefone, @NotNull(message = "O endereço é obrigatório") @Valid Endereco endereco,
			@NotEmpty(message = "O email é obrigatório") String email,
			@NotNull(message = "O tipo de pagamento é obrigatório") TipoDePagamento tipoDePagamento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.tipoDePagamento = tipoDePagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

}
