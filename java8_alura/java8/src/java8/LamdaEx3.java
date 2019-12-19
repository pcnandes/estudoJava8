package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;


public class LamdaEx3 {
	
	public static void main(String[] args) {
		List<String> palavras = Arrays.asList("Alura online", "editora casa do codigo", "caelum");
		
		// antigo usando o compare
		// palavras.sort((String s1, String s2) -> Integer.compare(s1.length(), s2.length()));

		// agora o comparator tem a interface funcional comparing onde se pode passar o criterio de comparação
		// leitura -> palavras, ordene comparando pelo tamanho
		palavras.sort(Comparator.comparing(s -> s.length()));
		
		
		// quebrando o lambda acima
		// Function<String, Integer> funcao = s -> s.length();
		
		// Function sem lambda
		Function<String, Integer> funcao = new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return s.length();
			}
		};
		// agroa passo a funcao para o comparing
		Comparator<String> comparador = Comparator.comparing(funcao);
		// e o comparator para o metodo sort
		palavras.sort(comparador);
		
		// Outra forma de fazer ainda mais simples 
		palavras.sort(Comparator.comparing(String::length));
		
		// outra forma de instanciar uma interface funcional
		Function<String, Integer> funcao2 = String::length;
		
		
		palavras.forEach(s -> System.out.println(s));
		// outra forma de fazer usando metodo reference
		palavras.forEach(System.out::println);
		
		
		// ordenando por outro criterio
		palavras.sort(String.CASE_INSENSITIVE_ORDER);
	}
	
}
