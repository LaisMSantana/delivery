package br.com.spring.delivery;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import br.com.spring.delivery.model.Endereco;
import br.com.spring.delivery.model.Cliente;
import br.com.spring.delivery.model.TipoDePagamento;
import br.com.spring.delivery.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteEndPointTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@MockBean
	ClienteService clienteService;
	@Autowired
	private MockMvc mockMvc;

	private Endereco endereco() {
		Endereco endereco = new Endereco(4L, "logradouro", "cidade", "estado", "88065422", "numero", "complemento");
		return endereco;
	}

	@Test
	public void quandoListarTodos_retorna200() throws Exception {
		Cliente cliente = new Cliente(3L, "Nome", "62016610000", "+55 (48) 9 9940-7364", this.endereco(),
				"teste@email.com", TipoDePagamento.DINHEIRO);

		when(clienteService.listarTodos()).thenReturn(java.util.List.of(cliente));

		mockMvc.perform(get("/clientes")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void quandoListarPorNome_retorna200() throws Exception {
		Cliente cliente = new Cliente(3L, "Nome", "62016610000", "+55 (48) 9 9940-7364", this.endereco(),
				"teste@email.com", TipoDePagamento.DINHEIRO);

		when(clienteService.pesquisarPorNome("Nome")).thenReturn(java.util.List.of(cliente));

		mockMvc.perform(get("/clientes")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void quandoListarPorIdInexistente_retorna404() throws Exception {
		Cliente cliente = new Cliente(3L, "Nome", "62016610000", "+55 (48) 9 9940-7364", this.endereco(),
				"teste@email.com", TipoDePagamento.DINHEIRO);

		when(clienteService.pesquisarPorId(3L)).thenReturn(java.util.Optional.of(cliente));

		mockMvc.perform(get("/clientes/{id}", 6)).andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void quandoListarPorId_retorna200() throws Exception {
		Cliente cliente = new Cliente(3L, "Nome", "62016610000", "+55 (48) 9 9940-7364", this.endereco(),
				"teste@email.com", TipoDePagamento.DINHEIRO);

		when(clienteService.pesquisarPorId(3L)).thenReturn(java.util.Optional.of(cliente));

		mockMvc.perform(get("/clientes/{id}", 3)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void quandoSalvar_retorna200() throws Exception {
		Cliente cliente = new Cliente("Nome", "62016610000", "+55 (48) 9 9940-7364", this.endereco(), "teste@email.com",
				TipoDePagamento.DINHEIRO);

		when(clienteService.salvar(cliente)).thenReturn(cliente);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);

		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isCreated()).andDo(print());
	}

	@Test
	public void quandoSalvarComNomeVazio_retorna400() throws Exception {
		Cliente cliente = new Cliente("", "12603107950", "+55 (48) 9 9940-7364", this.endereco(), "nome@email.com",
				TipoDePagamento.DINHEIRO);

		when(clienteService.salvar(cliente)).thenReturn(cliente);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);

		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void quandoSalvarComCpfInvalido_retorna400() throws Exception {
		Cliente cliente = new Cliente("Nome", "62016610099", "+55 (48) 9 9940-7364", this.endereco(), "nome@email.com",
				TipoDePagamento.DINHEIRO);

		when(clienteService.salvar(cliente)).thenReturn(cliente);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(cliente);

		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isBadRequest()).andDo(print());
	}


}
