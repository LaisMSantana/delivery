package br.com.spring.delivery;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.spring.delivery.model.Delivery;
import br.com.spring.delivery.model.Endereco;
import br.com.spring.delivery.model.ItemDelivery;
import br.com.spring.delivery.model.Pessoa;
import br.com.spring.delivery.model.Produto;
import br.com.spring.delivery.model.TipoDePagamento;
import br.com.spring.delivery.model.dto.DeliveryDto;
import br.com.spring.delivery.model.dto.ItemDeliveryDto;
import br.com.spring.delivery.model.dto.converter.DeliveryDtoConverter;
import br.com.spring.delivery.service.DeliveryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DeliveryEndPointTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@MockBean
	DeliveryService deliveryService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	DeliveryDtoConverter deliveryDtoConverter;

	private Pessoa pessoa() {
		Endereco endereco = new Endereco(4L, "logradouro", "cidade", "estado", "88065422", "numero", "complemento");
		Pessoa pessoa = new Pessoa(1L, "Nome", "62016610000", "+55 (48) 9 9940-7364", endereco,
				"teste@email.com", TipoDePagamento.DINHEIRO);
		return pessoa;
	}
	private List<ItemDelivery> itens() {
		Produto produto = new Produto(3L, "Nome", 2.55, "Descrição");
		ItemDelivery item = new ItemDelivery(1L,produto,4);
		return java.util.List.of(item);
	}
		
	@Test
	public void quandoListarTodos_retorna200() throws Exception {
		Delivery delivery = new Delivery(this.pessoa(), this.itens(), this.pessoa().getTipoDePagamento());

		when(deliveryService.listarTodos()).thenReturn(java.util.List.of(delivery));

		mockMvc.perform(get("/delivery")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void quandoListarPorIdInexistente_retorna404() throws Exception {
		Delivery delivery = new Delivery(this.pessoa(), this.itens(), this.pessoa().getTipoDePagamento());

		when(deliveryService.pesquisarPorId(1L)).thenReturn(java.util.Optional.of(delivery));

		mockMvc.perform(get("/delivery/{id}", 6)).andExpect(status().isNotFound()).andDo(print());
	}
	
	@Test
	public void quandoListarPorId_retorna200() throws Exception {
		Delivery delivery = new Delivery(this.pessoa(), this.itens(), this.pessoa().getTipoDePagamento());

		when(deliveryService.pesquisarPorId(1L)).thenReturn(java.util.Optional.of(delivery));

		mockMvc.perform(get("/delivery/{id}", 1)).andExpect(status().isOk()).andDo(print());
	}
	


}
