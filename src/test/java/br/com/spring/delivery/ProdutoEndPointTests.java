package br.com.spring.delivery;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

import br.com.spring.delivery.model.Produto;
import br.com.spring.delivery.service.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProdutoEndPointTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@MockBean
	ProdutoService produtoService;
	@Autowired
	private MockMvc mockMvc;

		
	@Test
	public void quandoListarTodos_retorna200() throws Exception {
		Produto produto = new Produto(3L, "Nome", 2.55, "Descrição");

		when(produtoService.listarTodos()).thenReturn(java.util.List.of(produto));

		mockMvc.perform(get("/produtos")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void quandoListarPorNome_retorna200() throws Exception {
		Produto produto = new Produto(3L, "Nome", 2.55, "Descrição");

		when(produtoService.pesquisarPorNome("Nome")).thenReturn(java.util.List.of(produto));

		mockMvc.perform(get("/produtos")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void quandoListarPorIdInexistente_retorna404() throws Exception {
		Produto produto = new Produto(3L, "Nome", 2.55, "Descrição");

		when(produtoService.pesquisarPorId(3L)).thenReturn(java.util.Optional.of(produto));

		mockMvc.perform(get("/produtos/{id}", 6)).andExpect(status().isNotFound()).andDo(print());
	}
	
	@Test
	public void quandoListarPorId_retorna200() throws Exception {
		Produto produto = new Produto(3L, "Nome", 2.55, "Descrição");

		when(produtoService.pesquisarPorId(3L)).thenReturn(java.util.Optional.of(produto));

		mockMvc.perform(get("/produtos/{id}", 3)).andExpect(status().isOk()).andDo(print());
	}
	
    @Test
    public void quandoSalvar_retorna200 () throws Exception {
    	Produto produto = new Produto(3L, "Nome", 2.55, "Descrição");

        when(produtoService.salvar(produto)).thenReturn(produto);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);


        mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    
    @Test
    public void quandoSalvarComNomeVazio_retorna400 () throws Exception {
    	Produto produto = new Produto(3L, "", 2.55, "Descrição");

        when(produtoService.salvar(produto)).thenReturn(produto);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);


        mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    
    
    @Test
    public void quandoSalvarComValorNulo_retorna400 () throws Exception {
    	Produto produto = new Produto(3L, "Nome", "Descrição");

        when(produtoService.salvar(produto)).thenReturn(produto);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);


        mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    
    @Test
    public void quandoSalvarComDescricaoVazio_retorna400 () throws Exception {
    	Produto produto = new Produto(3L, "Nome", 2.55, "");

        when(produtoService.salvar(produto)).thenReturn(produto);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(produto);


        mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}
