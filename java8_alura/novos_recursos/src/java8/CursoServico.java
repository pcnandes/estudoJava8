package java8;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CursoServico {
	
	LivroDAO dao = new LivroDAO();
	
	public List<Livro> buscarLivros() {
		HttpResponse<String> livros = dao.listarSincrono();
		return Stream.of(livros.body().split("\n"))
				.map(LivroServico::criar)
				.collect(Collectors.toList());
	}
	
	public List<Curso> listar() {
		List<Livro> livros = this.buscarLivros();
		Curso java = new Curso("Java", "Conceitos basicos de OO", "12 horas", livros.get(0));
		Curso spring = new Curso("Spring", "Novidades Spring", "12 horas", livros.get(2));
		Curso ejb = new Curso("EJB", "EJB avançado", "16 horas", livros.get(5));
		return List.of(java, spring, ejb);
	}
}
