package com.bcopstein.Emprestimos;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc

@ExtendWith(MockitoExtension.class)
public class EmprestimoTest {
        @Mock
        CalculoDeJuros calculoDeJuros;

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 8"
        })

        void custoTotalJuroSimples(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosSimples()
                                .build();
                when(calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn(emprestimo.getValor() * (emprestimo.getTaxa() + 0.01)
                                                * emprestimo.getNroParcelas());

                assertThat(emprestimo.custoTotal())
                                .isEqualTo(emprestimo.getValor() + (emprestimo.getValor() * emprestimo.IOF)
                                                + calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(),
                                                                emprestimo.getTaxa(),
                                                                emprestimo.getNroParcelas()));
        }

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 8"
        })

        void custoTotalJuroSimplesSemSeguro(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .semSeguro()
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosSimples()
                                .build();
                when(calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn(emprestimo.getValor() * (emprestimo.getTaxa())
                                                * emprestimo.getNroParcelas());

                assertThat(emprestimo.custoTotal())
                                .isEqualTo(emprestimo.getValor() + (emprestimo.getValor() * emprestimo.IOF)
                                                + calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(),
                                                                emprestimo.getTaxa(),
                                                                emprestimo.getNroParcelas()));
        }

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 10"
        })
        void custoTotalJuroComposto(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosCompostos()
                                .build();
                when(calculoDeJuros.jurosEmprestimoJurosCompostos(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn((valor * Math.pow(1 + (taxa + 0.01), parcelas)) - valor);

                assertThat(emprestimo.custoTotal())
                                .isEqualTo(valor + (valor * emprestimo.IOF)
                                                + calculoDeJuros.jurosEmprestimoJurosCompostos(valor,
                                                                taxa,
                                                                parcelas));
        }

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 10"
        })
        void custoTotalJuroCompostoSemSeguro(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .semSeguro()
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosCompostos()
                                .build();
                when(calculoDeJuros.jurosEmprestimoJurosCompostos(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn((valor * Math.pow(1 + taxa, parcelas)) - valor);

                assertThat(emprestimo.custoTotal())
                                .isEqualTo(valor + (valor * emprestimo.IOF)
                                                + calculoDeJuros.jurosEmprestimoJurosCompostos(valor,
                                                                taxa,
                                                                parcelas));
        }

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 8"
        })

        void valorParcelaJurosSimples(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosSimples()
                                .build();

                when(calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn(emprestimo.getValor() * (emprestimo.getTaxa() + 0.01)
                                                * emprestimo.getNroParcelas());

                assertThat(emprestimo.valorParcela())
                                .isEqualTo(emprestimo.custoTotal() / emprestimo.getNroParcelas());
        }

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 8"
        })

        void valorParcelaJurosSimplesSemSeguro(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosSimples()
                                .build();

                when(calculoDeJuros.jurosEmprestimoJurosSimples(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn(emprestimo.getValor() * emprestimo.getTaxa()
                                                * emprestimo.getNroParcelas());

                assertThat(emprestimo.valorParcela())
                                .isEqualTo(emprestimo.custoTotal() / emprestimo.getNroParcelas());
        }

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 8"
        })

        void valorParcelaJurosComposto(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosCompostos()
                                .build();

                when(calculoDeJuros.jurosEmprestimoJurosCompostos(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn((valor * Math.pow(1 + (taxa + 0.01), parcelas)) - valor);

                assertThat(emprestimo.valorParcela())
                                .isEqualTo(emprestimo.custoTotal() / emprestimo.getNroParcelas());
        }

        @ParameterizedTest
        @CsvSource({
                        "1000,0.35,10",
                        "2034,0.2,15",
                        "147999, 0.1, 8"
        })

        void valorParcelaJurosCompostoSemSeguro(double valor, double taxa, int parcelas) {
                Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                                .valor(valor)
                                .taxa(taxa)
                                .parcelas(parcelas)
                                .jurosCompostos()
                                .build();

                when(calculoDeJuros.jurosEmprestimoJurosCompostos(emprestimo.getValor(), emprestimo.getTaxa(),
                                emprestimo.getNroParcelas()))
                                .thenReturn((valor * Math.pow(1 + taxa, parcelas)) - valor);

                assertThat(emprestimo.valorParcela())
                                .isEqualTo(emprestimo.custoTotal() / emprestimo.getNroParcelas());
        }
}
