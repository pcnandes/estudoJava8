package java8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		AlunoServico alunoServico = new AlunoServico();
		TurmaServico turmaServico = new TurmaServico();
		// TesteHttpDAO dao = new TesteHttpDAO();
		LivroServico livroServico = new LivroServico();
		
		// chamada assincrona que será retornada por ultimo
		livroServico.listar();
		
		/* alunoServico.listar()
			.stream()
			.forEach(System.out::println); */
		
		// List<String> nomes = transformaEmListaDeNomes(alunoServico.listar());
		var nomes = transformaEmListaDeNomes(alunoServico.listar());
		System.out.println(nomes);
		
		agrupaPorCurso(turmaServico.listar());
		agrupaPorCursoUsandoFiltering(turmaServico.listar());
		
		
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
		
		/*
		try {
			dao.testarConexaoHttp();
		} catch (IOException | InterruptedException | URISyntaxException e) {
			e.printStackTrace();
		}*/
	}
	
	public static List<String> transformaEmListaDeNomes(List<Aluno> lista) {
		return lista
			.stream()
			// o map retornaria um array, criando um array de array. Já o flatMap retorna o próprio objeto modificado.
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
