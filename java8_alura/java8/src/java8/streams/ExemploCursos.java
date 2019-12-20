package java8.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


public class ExemploCursos {
	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));
		
		// ordena
		// cursos.sort(Comparator.comparing(c -> c.getAlunos()));
		// repare que tenho um comparador especial para Int
		cursos.sort(Comparator.comparingInt(Curso::getAlunos));
		
		// Com metodo reference nao é possivel escolher qual atributo do curso será impresso
		// cursos.forEach(System.out::println);
		cursos.forEach(c -> System.out.println(c.getNome()));
		
		System.out.println("-------");
		
		cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			// se eu usar esse sysout nao consigo concatenar mains nenhuma outra funcao
			// .forEach(c -> System.out.println(c.getNome()));;
			// recupero o total de alunos
			// .map(c -> c.getAlunos())
			.map(Curso::getAlunos)
			// como estou apenas o total de alunos percorrendo meu strem posso imprimir direto
			// .forEach(total -> System.out.println(total));
			.forEach(System.out::println);
		
		System.out.println("-------");
		
		int sum = cursos.stream()
			.filter(c -> c.getAlunos() >= 100)
			.mapToInt(Curso::getAlunos)
			.sum();
		
		System.out.println("total " + sum);
		
	}
	
	
}