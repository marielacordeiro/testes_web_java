package com.bcopstein.Emprestimos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import com.bcopstein.Emprestimos.Emprestimo.Builder;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmprestimoTest {
    @Mock
    CalculoDeJuros calculoDeJuros;

    @Test
    void custoTotalJuroSimplesComSeguro() {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .comSeguro()
                .valor(1000)
                .taxa(0.35)
                .parcelas(10)
                .jurosSimples()
                .build();
        when(calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(), emprestimo.getTaxa(),
                emprestimo.getNroParcelas()))
                .thenReturn(350.0);
        assertThat(emprestimo.custoTotal())
                .isEqualTo(emprestimo.getValor() + (emprestimo.getValor() * emprestimo.IOF) + 350.0);
    }

    @Test
    void custoTotalJuroCompostoComSeguro() {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .comSeguro()
                .valor(1000)
                .taxa(0.35)
                .parcelas(10)
                .jurosCompostos()
                .build();
        when(calculoDeJuros.jurosEmprestimoJurosCompostos(emprestimo.getValor(), emprestimo.getTaxa(),
                emprestimo.getNroParcelas()))
                .thenReturn(350.0);
        assertThat(emprestimo.custoTotal())
                .isEqualTo(emprestimo.getValor() + (emprestimo.getValor() * emprestimo.IOF) + 350.0);
    }

    @Test
    void custoTotalJuroSimplesSemSeguro() {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .valor(1000)
                .taxa(0.35)
                .parcelas(10)
                .jurosSimples()
                .build();
        when(calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(), emprestimo.getTaxa(),
                emprestimo.getNroParcelas()))
                .thenReturn(350.0);
        assertThat(emprestimo.custoTotal())
                .isEqualTo(emprestimo.getValor() + (emprestimo.getValor() * emprestimo.IOF) + 350.0);
    }

    @Test
    void custoTotalJuroCompostoSemSeguro() {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .valor(1000)
                .taxa(0.35)
                .parcelas(10)
                .jurosCompostos()
                .build();
        when(calculoDeJuros.jurosEmprestimoJurosCompostos(emprestimo.getValor(), emprestimo.getTaxa(),
                emprestimo.getNroParcelas()))
                .thenReturn(350.0);
        assertThat(emprestimo.custoTotal())
                .isEqualTo(emprestimo.getValor() + (emprestimo.getValor() * emprestimo.IOF) + 350.0);
    }

    @Test
    void valorParcela() {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .comSeguro()
                .valor(1000)
                .taxa(0.35)
                .parcelas(10)
                .jurosSimples()
                .build();
        when(calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(), emprestimo.getTaxa(),
                emprestimo.getNroParcelas()))
                .thenReturn(350.0);
        assertThat(emprestimo.valorParcela())
                .isEqualTo((emprestimo.getValor() + (emprestimo.getValor() * emprestimo.IOF) + 350.0)
                        / emprestimo.getNroParcelas());
    }
}
