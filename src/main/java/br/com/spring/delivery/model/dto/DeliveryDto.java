package br.com.spring.delivery.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import br.com.spring.delivery.model.Delivery;
import br.com.spring.delivery.model.TipoDePagamento;

public class DeliveryDto {

	
	private Long id;
	@NotNull
	private Long idCliente;
	@NotNull
	private List<ItemDeliveryDto> itemDeliveriesDto;
	private double valorTotal;	
	@Enumerated(EnumType.STRING)
	private TipoDePagamento tipoDePagamento;

	public DeliveryDto() {
	}

	public DeliveryDto(Delivery delivery) {
		this.id = delivery.getId();
		this.idCliente = delivery.getCliente().getId();
		this.itemDeliveriesDto = new ArrayList<>();
		this.itemDeliveriesDto
				.addAll(delivery.getItemDeliveries().stream().map(ItemDeliveryDto::new).collect(Collectors.toList()));
		this.valorTotal = delivery.getValorTotal();
		this.tipoDePagamento = delivery.getTipoDePagamento();
	}
	
	public DeliveryDto(@NotNull Long idCliente, @NotNull List<ItemDeliveryDto> itemDeliveriesDto) {
		super();
		this.idCliente = idCliente;
		this.itemDeliveriesDto = itemDeliveriesDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<ItemDeliveryDto> getItemDeliveriesDto() {
		return itemDeliveriesDto;
	}

	public void setItemDeliveriesDto(List<ItemDeliveryDto> itemDeliveriesDto) {
		this.itemDeliveriesDto = itemDeliveriesDto;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public TipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}
	
	

}
