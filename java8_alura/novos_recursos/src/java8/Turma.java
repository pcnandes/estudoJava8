package java8;

import java.time.LocalDate;
import java.util.List;

public class Turma {
	private LocalDate inicio;
	private LocalDate fim;
	private Curso curso;
	private List<Aluno> alunos;
	
	// ... recebe alunos separados por virgula e transforma em um array
	// precisa ser o ultimo argumento
	public Turma(LocalDate inicio, LocalDate fim, Curso curso, Aluno ...aluno) {
		super();
		this.inicio = inicio;
		this.fim = fim;
		this.curso = curso;
		this.alunos = List.of(aluno);
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		return "Turma [inicio=" + inicio + ", fim=" + fim + ", curso=" + curso + ", alunos=" + alunos + "]";
	}

}
