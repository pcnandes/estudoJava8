package java8;

import java.util.List;

public class CursoServico {
	Curso java = new Curso("Java", "Conceitos basicos de OO", "12 horas");
	Curso spring = new Curso("Spring", "Novidades Spring", "12 horas");
	Curso ejb = new Curso("EJB", "EJB avançado", "16 horas");
	
	public List<Curso> listar() {
		return List.of(java, spring, ejb);
	}
}
