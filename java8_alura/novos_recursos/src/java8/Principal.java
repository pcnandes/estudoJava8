package java8;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

	public static void main(String[] args) {
		// AlunoServico alunoServico = new AlunoServico();
		var alunoServico = new AlunoServico();
		
		alunoServico.listar()
			.stream()
			.forEach(System.out::println);
		
		// List<String> nomes = transformaEmListaDeNomes(alunoServico.listar());
		var nomes = transformaEmListaDeNomes(alunoServico.listar());
		System.out.println(nomes);
		
		
		// Optional<Aluno> aluno = alunoServico.listarPorCpf(1L);
		var aluno = alunoServico.listarPorCpf(1L);
		/* ANTIGO Optioal
		if(aluno.isPresent()) {
			System.out.println("Aluno encontrado: " + aluno.get());
		} else {
			System.out.println("CPF não encontrado!");
		}*/
		
		// NOVO Optioal
		aluno.ifPresentOrElse(System.out::println,
				() -> System.out.println("CPF não encontrado!"));
		
		
		// encadeando optionals -> or -> se for null ele executa uma nova função. Caso encontre os proximos serão ignorados
		// Optional<Aluno> alunoRecuperado = alunoServico.listarPorCpf(0L)
		var alunoRecuperado = alunoServico.listarPorCpf(0L)
				.or(() -> alunoServico.listarPorCpf(11111L))
				.or(() -> alunoServico.listarPorCpf(1L));
		
		System.out.println("Aluno recuperado " + alunoRecuperado.get());
		
		
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
