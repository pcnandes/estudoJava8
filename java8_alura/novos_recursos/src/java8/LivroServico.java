package java8;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class LivroServico {
	
	LivroDAO dao = new LivroDAO();
	
	public static Livro criar(String linha) {
		String[] split = linha.split(",");
		String nome = split[0];
		String autor = split[2];
		return new Livro(nome, autor);
	}
	
	public CompletableFuture<HttpResponse<String>> listar() {
		return dao.listarAssincrono();
	}
}
