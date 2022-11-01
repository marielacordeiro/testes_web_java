package com.bcopstein.Emprestimos;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegracaoTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void emprestimoJurosSimplesIntegrityTest() throws Exception {

		this.mockMvc.perform(get("/emprestimo/jurosSimples?valor=1000&parcelas=10"))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason(containsString(
						"Required request parameter 'taxa' for method parameter type Double is not present")));

		this.mockMvc.perform(get("/emprestimo/jurosSimples?valor=1000&taxa=0.032"))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason(containsString(
						"Required request parameter 'parcelas' for method parameter type Integer is not present")));

		this.mockMvc.perform(get("/emprestimo/jurosSimples?parcelas=10&taxa=0.032"))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason(containsString(
						"Required request parameter 'valor' for method parameter type Double is not present")));
	}

	@Test
	public void emprestimoJurosCompostoIntegrityTest() throws Exception {

		this.mockMvc.perform(get("/emprestimo/jurosCompostos?valor=1000&taxa=0.032"))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason(containsString(
						"Required request parameter 'parcelas' for method parameter type Integer is not present")));

		this.mockMvc.perform(get("/emprestimo/jurosCompostos?valor=1000&parcelas=10"))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason(containsString(
						"Required request parameter 'taxa' for method parameter type Double is not present")));

		this.mockMvc.perform(get("/emprestimo/jurosCompostos?parcelas=10&taxa=0.032"))
				.andExpect(status().isBadRequest())
				.andExpect(status().reason(containsString(
						"Required request parameter 'valor' for method parameter type Double is not present")));
	}

	@ParameterizedTest
	@CsvSource({
			"true,false,120,0.02,6,148.8,24.8",
			"true,false,339000,0.50,2,705120.0,352560.0",
			"true,false,0.88,0.12,1,1.0472,1.0472",
			"true,false,5600,0.5,17,54488.0,3205.176470588235",
			"true,false,0,0.04,1,0,0",
			"true,false,10,2,2,50.8,25.4",
	})
	public void emprestimoJurosSimplesTest(boolean segurado, boolean jurosCompostos, double valor, double taxa,
			int nroParcelas, double valorTotal, double valorParcela) throws Exception {

		EmprestimoDTO esperado = new EmprestimoDTO(segurado, jurosCompostos, valor, taxa, nroParcelas, valorTotal,
				valorParcela);

		MvcResult result = this.mockMvc
				.perform(get("/emprestimo/jurosSimples?valor=" + valor + "&parcelas=" + nroParcelas + "&taxa=" + taxa))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		EmprestimoDTO recebido = new Gson().fromJson(content, EmprestimoDTO.class);

		assertEquals(esperado.isSegurado(), recebido.isSegurado());
		assertEquals(esperado.isJurosCompostos(), recebido.isJurosCompostos());
		assertEquals(esperado.getValor(), recebido.getValor());
		assertEquals(esperado.getTaxa(), recebido.getTaxa());
		assertEquals(esperado.getNroParcelas(), recebido.getNroParcelas());
		assertEquals(esperado.getValorTotal(), recebido.getValorTotal());
		assertEquals(esperado.getValorParcela(), recebido.getValorParcela());
	}

	@ParameterizedTest
	@CsvSource({
			"true,true,120,0.02,6,150.48627558348,25.08104593058",
			"true,true,339000,0.50,2,793293.9,396646.95",
			"true,true,0.88,0.12,1,1.0472,1.0472",
			"true,true,5600,0.5,17,6177599.554130504,363388.20906650025",
			"true,true,0,0.04,1,0,0",
			"true,true,10,2,2,91.20099999999998,45.60049999999999",
	})
	public void emprestimoJurosCompostoTest(boolean segurado, boolean jurosCompostos, double valor, double taxa,
			int nroParcelas, double valorTotal, double valorParcela) throws Exception {

		EmprestimoDTO esperado = new EmprestimoDTO(segurado, jurosCompostos, valor, taxa, nroParcelas, valorTotal,
				valorParcela);

		MvcResult result = this.mockMvc
				.perform(
						get("/emprestimo/jurosCompostos?valor=" + valor + "&parcelas=" + nroParcelas + "&taxa=" + taxa))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		EmprestimoDTO recebido = new Gson().fromJson(content, EmprestimoDTO.class);

		assertEquals(esperado.isSegurado(), recebido.isSegurado());
		assertEquals(esperado.isJurosCompostos(), recebido.isJurosCompostos());
		assertEquals(esperado.getValor(), recebido.getValor());
		assertEquals(esperado.getTaxa(), recebido.getTaxa());
		assertEquals(esperado.getNroParcelas(), recebido.getNroParcelas());
		assertEquals(esperado.getValorTotal(), recebido.getValorTotal());
		assertEquals(esperado.getValorParcela(), recebido.getValorParcela());
	}
}