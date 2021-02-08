package br.com.spring.delivery.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo pessoa é obrigatório")
	@OneToOne
	private Pessoa pessoa;
	
	@NotNull(message = "Lista de itens é obrigatório")
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemDelivery> itemDeliveries;
	
	@NotNull
	private double valorTotal;
	
	@NotNull
	private LocalDateTime dataPedido = LocalDateTime.now();
	
	@NotNull(message = "O campo tipo de pagamento é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoDePagamento tipoDePagamento;

	public Delivery() {
	}

	public Delivery(@NotNull Pessoa pessoa, @NotNull List<ItemDelivery> itemDeliveries,
			TipoDePagamento tipoDePagamento) {
		this.pessoa = pessoa;
		this.itemDeliveries = itemDeliveries;
		this.tipoDePagamento = tipoDePagamento;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<ItemDelivery> getItemDeliveries() {
		return itemDeliveries;
	}

	public void setItemDeliveries(List<ItemDelivery> itemDeliveries) {
		this.itemDeliveries = itemDeliveries;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public TipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

	public void calcularValorTotal() {
		double valorTotal = 0;
		for (int i = 0; i < this.itemDeliveries.size(); i++) {
			valorTotal += this.itemDeliveries.get(i).getValorTotal();
		}
		this.setValorTotal(valorTotal);
	}
}
