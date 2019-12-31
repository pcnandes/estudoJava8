package java8;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

	public static void main(String[] args) {
		AlunoServico alunoServico = new AlunoServico();
		TurmaServico turmaServico = new TurmaServico();
		
		/* alunoServico.listar()
			.stream()
			.forEach(System.out::println); */
		
		List<String> nomes = transformaEmListaDeNomes(alunoServico.listar());
		System.out.println(nomes);
		
		agrupaPorCurso(turmaServico.listar());
		agrupaPorCursoUsandoFiltering(turmaServico.listar());
	}
	
	public static List<String> transformaEmListaDeNomes(List<Aluno> lista) {
		return lista
			.stream()
			// o map retornaria um array, criando um array de array. JÃ¡ o flatMap retorna o prÃ³prio objeto modificado.
			.flatMap(a -> Stream.ofNullable(a.getNome()))
			.map(s -> s.toUpperCase())
			.collect(Collectors.toList());
	}

	public static void agrupaPorCurso(List<Turma> turmas) {
		// agrupar por curso
		Map<Curso, List<Turma>> turmasPorCurso = turmas.stream()
				.filter(a -> LocalDate.of(2019, 06, 10).equals(a.getInicio()))
				// grouping by retorna uma map
				.collect(Collectors.groupingBy(Turma::getCurso));
		
		System.out.println("Relação de turmas por curso" + turmasPorCurso);
	}
	
	public static void agrupaPorCursoUsandoFiltering(List<Turma> turmas) {
		// agrupar por curso
		Map<Curso, List<Turma>> turmasPorCurso = turmas.stream()
				.collect(Collectors.groupingBy(Turma::getCurso,
						// com filtering eu consigo filtrar direto dentro da lista, com isso trago os cursos, mas nao trago os alunos
						Collectors.filtering(a -> a.getInicio().equals(LocalDate.of(2019, 06, 04)),
								Collectors.toList())));
		
		System.out.println("Relação de turmas por curso" + turmasPorCurso);
	}
}
