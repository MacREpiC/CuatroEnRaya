package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FichaTest {

	@ParameterizedTest(name = "Cuando llamamos al método toString para la instancia: {0} devuelve la cadena esperada: {1}")
	@CsvSource({"AZUL, A", "VERDE, V"})
	void toStringDevuelveLaCadenaEsperada(Ficha ficha, String nombre) {
		assertEquals(nombre, ficha.toString());
	}

}
