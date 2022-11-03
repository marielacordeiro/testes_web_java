package com.bcopstein.Emprestimos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegracaoTest {

    CalculoDeJuros calculoDeJuros = new CalculoDeJuros();

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

        assertThat(emprestimo.custoTotal())
                .isEqualTo(valor + (valor * emprestimo.IOF)
                        + calculoDeJuros.jurosEmprestimoJurosSimples(valor, taxa,
                                parcelas));
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

        calculoDeJuros.setSeguro(false);

        assertThat(emprestimo.custoTotal())
                .isEqualTo(valor + (valor * emprestimo.IOF)
                        + calculoDeJuros.jurosEmprestimoJurosSimples(valor, taxa,
                                parcelas));
    }

    @ParameterizedTest
    @CsvSource({
            "1000,0.35,10",
            "2034,0.2,15",
            "147999, 0.1, 8"
    })

    void custoTotalJuroComposto(double valor, double taxa, int parcelas) {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .valor(valor)
                .taxa(taxa)
                .parcelas(parcelas)
                .jurosCompostos()
                .build();

        assertThat(emprestimo.custoTotal()).isEqualTo(valor + (valor * emprestimo.IOF)
                + calculoDeJuros.jurosEmprestimoJurosCompostos(valor, taxa,
                        parcelas));
    }

    @ParameterizedTest
    @CsvSource({
            "1000,0.35,10",
            "2034,0.2,15",
            "147999, 0.1, 8"
    })

    void custoTotalJuroCompostoSemSeguro(double valor, double taxa, int parcelas) {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .semSeguro()
                .valor(valor)
                .taxa(taxa)
                .parcelas(parcelas)
                .jurosCompostos()
                .build();

        calculoDeJuros.setSeguro(false);

        assertThat(emprestimo.custoTotal()).isEqualTo(valor + (valor * emprestimo.IOF)
                + calculoDeJuros.jurosEmprestimoJurosCompostos(valor, taxa,
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

        assertThat(emprestimo.valorParcela())
                .isEqualTo(emprestimo.custoTotal() / parcelas);
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

        calculoDeJuros.setSeguro(false);

        assertThat(emprestimo.valorParcela())
                .isEqualTo(emprestimo.custoTotal() / parcelas);
    }

    @ParameterizedTest
    @CsvSource({
            "1000,0.35,10",
            "2034,0.2,15",
            "147999, 0.1, 8"
    })

    void valorParcelaJurosCompostos(double valor, double taxa, int parcelas) {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .valor(valor)
                .taxa(taxa)
                .parcelas(parcelas)
                .jurosCompostos()
                .build();

        assertThat(emprestimo.valorParcela())
                .isEqualTo(emprestimo.custoTotal() / parcelas);
    }

    @ParameterizedTest
    @CsvSource({
            "1000,0.35,10",
            "2034,0.2,15",
            "147999, 0.1, 8"
    })

    void valorParcelaJurosCompostosSemSeguro(double valor, double taxa, int parcelas) {
        Emprestimo emprestimo = new Emprestimo.Builder(calculoDeJuros)
                .valor(valor)
                .taxa(taxa)
                .parcelas(parcelas)
                .jurosCompostos()
                .build();

        calculoDeJuros.setSeguro(false);

        assertThat(emprestimo.valorParcela())
                .isEqualTo(emprestimo.custoTotal() / parcelas);
    }
}
