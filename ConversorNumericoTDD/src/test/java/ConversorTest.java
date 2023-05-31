import Conversor.ConversorNumerico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConversorTest
{
	ConversorNumerico conversor;

	@BeforeEach
	void setUp()
	{
		 conversor = new ConversorNumerico();
	}


	@Test
	@DisplayName("Teste da conversão de Romano para Indo-Arábico")
	public void testConversaoRomanoParaDecimal()
	{
		assertEquals("1021", conversor.converterParaArabicos("MXXI"));
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			conversor.converterParaArabicos("XXXXI");
		});
		assertEquals("Sequência informada inválida. Ou vazia ou nula ou não atende os critérios dos Romanos", exception.getMessage());

	}

	@Test
	@DisplayName("Regra da soma de caracteres")
	public void testRegraDaSoma()
	{
		assertEquals("3", conversor.converterParaArabicos("III"));
		assertEquals("18", conversor.converterParaArabicos("XVIII"));
		assertEquals("3000", conversor.converterParaArabicos("MMM"));
	}

	@Test
	@DisplayName("Regra da subtração de caracteres")
	public void testRegraDeSubtracao()
	{
		assertEquals("9", conversor.converterParaArabicos("IX"));
		assertEquals("40", conversor.converterParaArabicos("XL"));
		assertEquals("400", conversor.converterParaArabicos("CD"));
	}

	@Test
	@DisplayName("Regra da repetição de caracteres")
	public void testRegraDaRepeticao()
	{
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			conversor.converterParaArabicos("CCCC");
		});
		assertEquals("Sequência informada inválida. Ou vazia ou nula ou não atende os critérios dos Romanos", exception.getMessage());
		assertEquals("3000", conversor.converterParaArabicos("MMM"));
	}

	@Test
	@DisplayName("Regra do uso dos símbolos")
	public void testRegraSimbolos()
	{
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			conversor.converterParaArabicos("IC");
		});
		assertEquals("Sequência informada inválida. Ou vazia ou nula ou não atende os critérios dos Romanos", exception.getMessage());
	}

	@Test
	public void testConversaoDecimalParaRomano()
	{
		assertEquals("Valor informado fora do limite desejado (Maior que 0 e Menor que 4000)", conversor.converterParaRomanos(4999));
		assertEquals("CM", conversor.converterParaRomanos(900));

	}

	@Test
	@DisplayName("Regra conversão do zero para romano")
	public void testLimiteInferiorDecimal()
	{
		assertEquals("Valor informado fora do limite desejado (Maior que 0 e Menor que 4000)", conversor.converterParaRomanos(0));
	}

	@Test
	@DisplayName("Regra conversão além do limite supurior decimal para romano")
	public void testLimiteSuperiorDecimal()
	{
		assertEquals("Valor informado fora do limite desejado (Maior que 0 e Menor que 4000)",
				conversor.converterParaRomanos(4000));
	}


	@DisplayName("Conjunto 1 de testes parametrizados com 10 parâmetros")
	@ParameterizedTest
	@CsvSource({
			"I, 1",
			"II, 2",
			"III, 3",
			"IV, 4",
			"V, 5",
			"XVI, 16",
			"LXVII, 67",
			"DCVIII, 608",
			"CMXCIX, 999",
			"MM, 2000"})
	public void testConversaoRomanoParaArabico(String romano, String decimal)
	{
		assertEquals(decimal, ConversorNumerico.converterParaArabicos(romano));
	}

	@DisplayName("Conjunto 2 de testes parametrizados com 10 parâmetros")
	@ParameterizedTest
	@CsvSource({
			"11, XI",
			"42, XLII",
			"83, LXXXIII",
			"140, CXL",
			"500, D",
			"602, DCII",
			"617, DCXVII",
			"888, DCCCLXXXVIII",
			"908, CMVIII",
			"1000, M"})
	public void testConversaoArabicoParaRomano(int decimal, String romano)
	{
		assertEquals(romano, ConversorNumerico.converterParaRomanos(decimal));
	}
}
