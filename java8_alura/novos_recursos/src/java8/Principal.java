package java8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

	public static void main(String[] args) {
		AlunoServico alunoServico = new AlunoServico();
		
		alunoServico.listar()
			.stream()
			.forEach(System.out::println);
		
		List<String> nomes = transformaEmListaDeNomes(alunoServico.listar());
		System.out.println(nomes);
	}
	
	public static List<String> transformaEmListaDeNomes(List<Aluno> lista) {
		return lista
			.stream()
			// o map retornaria um array, criando um array de array. Já o flatMap retorna o próprio objeto modificado.
			.flatMap(a -> Stream.ofNullable(a.getNome()))
			.map(s -> s.toUpperCase())
			.collect(Collectors.toList());
	}

}
