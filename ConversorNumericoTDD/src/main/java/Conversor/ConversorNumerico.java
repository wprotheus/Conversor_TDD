package Conversor;

public class ConversorNumerico
{
	private static final String[] ROMANOS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	private static final int[] DECIMAIS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

	public static String converterParaArabicos(String s)
	{
		StringBuilder sb = new StringBuilder();
		int result = 0;
		if (s == null || s.isEmpty() || !s.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"))
			throw new IllegalArgumentException("Sequência informada inválida. Ou vazia ou nula ou não atende os critérios dos Romanos");
		int i = 0;
		for (int j = 0; j < ROMANOS.length; j++)
		{
			while (s.startsWith(ROMANOS[j], i))
			{
				result += DECIMAIS[j];
				i += ROMANOS[j].length();
			}
		}
		return sb.append(result).toString();
	}

	public static String converterParaRomanos(int decimal)
	{
		StringBuilder resultado = new StringBuilder();
		if (decimal > 0 && decimal < 4000)
		{
			for (int i = 0; i < DECIMAIS.length; i++)
			{
				int parteInteira = decimal / DECIMAIS[i];
				decimal -= DECIMAIS[i] * parteInteira;
				for (int j = 0; j < parteInteira; j++)
				{
					resultado.append(ROMANOS[i]);
				}
			}
		}
		else resultado.append("Valor informado fora do limite desejado (Maior que 0 e Menor que 4000)");
		return resultado.toString();
	}
}