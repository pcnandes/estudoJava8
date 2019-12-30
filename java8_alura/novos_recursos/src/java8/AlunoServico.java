package java8;

import java.util.List;

public class AlunoServico {
	Aluno joao = new Aluno("João", 1L);
	Aluno fernanda = new Aluno("Fernanda", 2L);
	Aluno nulo = new Aluno(null, 2L);
	
	public List<Aluno> listar() {
		// return Collections.unmodifiableList(Arrays.asList(joao, fernanda));
		return List.of(joao, fernanda, nulo);
	}
}
