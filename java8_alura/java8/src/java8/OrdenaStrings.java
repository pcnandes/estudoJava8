package java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;


// Default Methods
public class OrdenaStrings {

	public static void main(String[] args) {
		List<String> palavras = new ArrayList<>();
		palavras.add("Alura online");
		palavras.add("editora casa do codigo");
		palavras.add("caelum");
		
		// comparacao padrao do string
		Collections.sort(palavras);
		System.out.println(palavras);
		
		// caparador por tamanho
		Comparator<String> comparador = new ComparadorPorTamanho();
		Collections.sort(palavras, comparador);
		System.out.println(palavras);
		
		// Java8
		// Comparator<String> comparador = new ComparadorPorTamanho();
		palavras.sort(comparador);
		System.out.println(palavras);
		
//		for (String s : palavras) {
//			System.out.println(s);
//		}
		
		// forma de percorrer uma lista e imprimir seus atributos
		Consumer<String> consumidor = new ImprimeNaLinha();
		palavras.forEach(consumidor);
	}

}

class ComparadorPorTamanho implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		if(s1.length() < s2.length()) return 1;
		if(s1.length() > s2.length()) return -1;
		return 0;
	}
	
}

class ImprimeNaLinha implements Consumer<String>{

	@Override
	public void accept(String s) {
		System.out.println(s);
	}
	
}