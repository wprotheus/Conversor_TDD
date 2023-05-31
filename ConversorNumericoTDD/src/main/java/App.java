import Conversor.ConversorNumerico;

public class App
{
	public static void main(String[] args)
	{
		ConversorNumerico conversor = new ConversorNumerico();

		String[] romanos = {"MMXXI", " ", "IIX", "MMM"};
		for (String romano : romanos)
		{
			String decimal = conversor.converterParaArabicos(romano);
			if (!decimal.startsWith("Seq")) System.out.println("Romano: " + romano + " -> Decimal: " + decimal);
			else System.out.println("Romano: " + romano + "\t-> " + decimal);
		}
		System.out.println("<------------------------------>");
		int[] decimais = {5000, 0, 2025, 999};
		for (int decimal : decimais)
		{
			String romano = conversor.converterParaRomanos(decimal);
			if (!romano.isEmpty()) System.out.println("Decimal: " + decimal + " -> Romano: " + romano);
		}
	}
}
