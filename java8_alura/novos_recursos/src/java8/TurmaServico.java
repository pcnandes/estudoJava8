package java8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

public class TurmaServico {
	AlunoServico alunoServico = new AlunoServico();
	CursoServico cursoServico = new CursoServico();
	
	public List<Turma> listar() throws IOException, InterruptedException, URISyntaxException {
		return List.of(
				new Turma(LocalDate.of(2019, 06, 10), LocalDate.of(2019, 06, 17), 
						cursoServico.listar().get(0), 
						alunoServico.listar().get(0),
						alunoServico.listar().get(1)),
				new Turma(LocalDate.of(2019, 06, 18), LocalDate.of(2019, 06, 25), 
						cursoServico.listar().get(1), 
						alunoServico.listar().get(2)));
				
	}
}
