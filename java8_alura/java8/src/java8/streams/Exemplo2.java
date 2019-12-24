package java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Exemplo2 {
	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));
		
		
		// Optional ajuda a trabalhar com Null
		Optional<Curso> optionalCurso = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.findAny();
		
		// da uma excessão caso nao exista o curso
		Curso curso = optionalCurso.get();
		
		// retorna o curso ou outra coisa, caso nao exista o curso
		optionalCurso.orElse(null);
		
		// se existir o curso faz alguma coisa
		optionalCurso.ifPresent(c -> System.out.println(c.getNome()));
		
		
		// concatenando com stream. Todas stream retorna um optional e o ifPresent permite executar algo, caso exista o valor
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.findAny()
			.ifPresent(c -> System.out.println(c.getNome()));
		
		// Quanto faço um average ele retorna um double. No caso de stream retorna um OptionalDouble
		OptionalDouble average = cursos.stream()
		.filter(c -> c.getAlunos() >= 100)
		.mapToInt(Curso::getAlunos)
		.average();
		
		// convertendo em lista
		List<Curso> resultado = cursos.stream()
		.filter(c -> c.getAlunos() >= 100)
		.collect(Collectors.toList());
		
		// Convertendo para um map
		// o toMap recebe duas funcoes uma para o indice e outro para o valor
		Map<String, Integer> resultMap = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.collect(Collectors.toMap(
					c -> c.getNome(),
					c -> c.getAlunos()));
		
		// imrpimindo direto
		cursos.stream()
		.filter(c -> c.getAlunos() >= 100)
		.collect(Collectors.toMap(
				c -> c.getNome(),
				c -> c.getAlunos()))
		.forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos + " alunos"));
	}

}