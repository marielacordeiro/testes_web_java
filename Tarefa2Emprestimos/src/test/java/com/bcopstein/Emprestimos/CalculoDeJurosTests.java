package com.bcopstein.Emprestimos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculoDeJurosTests {
    @Test
    public void construtor() {
        CalculoDeJuros calculadora = new CalculoDeJuros();
        double response = calculadora.getTaxaSeguro();
        assertEquals(0.01, response);
    }

    @Test
    public void setTaxa() {
        CalculoDeJuros calculadora = new CalculoDeJuros();
        calculadora.setTaxaSeguro(0.05);
        assertEquals(0.05, calculadora.getTaxaSeguro());
    }

    @Test
    public void getSeguro() {
        CalculoDeJuros calculadora = new CalculoDeJuros();
        boolean response = calculadora.comSeguro();
        assertTrue(response);
    }

    @Test
    public void setSeguro() {
        CalculoDeJuros calculadora = new CalculoDeJuros();
        calculadora.setSeguro(false);
        assertFalse(calculadora.comSeguro());
    }

    @Test
    public void jurosSimples() {
        CalculoDeJuros calculator = new CalculoDeJuros();
        double response = calculator.jurosEmprestimoJurosSimples(5.00, 0.05, 3);
        assertEquals(0.9000000000000001, response);
    }

    public void jurosSimples2() {
        CalculoDeJuros calculator = new CalculoDeJuros();
        calculator.setSeguro(false);
        double response = calculator.jurosEmprestimoJurosSimples(5.00, 0.05, 3);
        assertEquals(0.75, response);
    }

    @Test
    public void jurosCompostos() {
        CalculoDeJuros calculator = new CalculoDeJuros();
        double response = calculator.jurosEmprestimoJurosCompostos(5.00, 0.05, 3);
        assertEquals(0.9550799999999997, response);
    }

    @Test
    public void jurosCompostos2() {
        CalculoDeJuros calculator = new CalculoDeJuros();
        calculator.setSeguro(false);
        double response = calculator.jurosEmprestimoJurosCompostos(5.00, 0.05, 3);
        assertEquals(0.788125, response);
    }

}