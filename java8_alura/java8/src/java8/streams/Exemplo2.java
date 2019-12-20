package java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		// se existir o curso faz alguma coisa
		optionalCurso.ifPresent(c -> System.out.println(c.getNome()));
		
		
		// concatenando com stream
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.findAny()
			.ifPresent(c -> System.out.println(c.getNome()));
	}

}