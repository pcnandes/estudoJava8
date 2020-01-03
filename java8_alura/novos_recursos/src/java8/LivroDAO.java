package java8;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;



public class LivroDAO {
	
	public HttpClient criarHttpClient() {
		return HttpClient.newBuilder()
				.followRedirects(HttpClient.Redirect.ALWAYS)
				.build();
	}
	
	public HttpRequest criarRequisicao() {
		try {
			return HttpRequest.newBuilder()
					.uri(new URI("https://turini.github.io/livro-java-9/books.csv"))
					.GET()
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public HttpResponse<String> listarSincrono(){
		try {
			return criarHttpClient().send(criarRequisicao(), HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public CompletableFuture<HttpResponse<String>> listarAssincrono(){
		try {
			return criarHttpClient().sendAsync(criarRequisicao(), HttpResponse.BodyHandlers.ofString())
					.whenComplete((r, s) -> System.out.println(r.body()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	public HttpResponse<String> criarRequisicao() throws IOException, InterruptedException, URISyntaxException {
		HttpClient client = HttpClient.newBuilder()
				.followRedirects(HttpClient.Redirect.ALWAYS)
				.build();

		HttpRequest req = HttpRequest.newBuilder(new URI("https://turini.github.io/livro-java-9/books.csv"))
				.GET()
				.build();
		
		return client.send(req, HttpResponse.BodyHandlers.ofString());
	}*/
}
