package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Lambda {

	public static void main(String[] args) {
		List<String> palavras = Arrays.asList("Alura online", "editora casa do codigo", "caelum");
		
		
		
		
		// funcao anonima. Implemento a interface direto
		Consumer<String> consumidor = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		};
		palavras.forEach(consumidor);
		
		// posso tbm passar direto a implementacao
		palavras.forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		});
		
		// LAMBDA -> Como forEach espera por um consumer e a interface consumer
		// exige a implementacao de apenas um método, o mesmo pode ser chamado direto
		// sem precisar dar um new nele usando lambdas	
		palavras.forEach((String s) -> {
			System.out.println(s);
		});
		
		// nao preciso declarar o tipo da variavel
		// nao preciso abrir e fechar parenteses
		// como tenho apenas uma linha nao preciso dos parenteses
		palavras.forEach(s -> System.out.println(s));
		
		
		// outro lambda com ordenacao
		palavras.sort((String s1, String s2) -> Integer.compare(s1.length(), s2.length()));
		palavras.forEach(s -> System.out.println(s));
	}
}
