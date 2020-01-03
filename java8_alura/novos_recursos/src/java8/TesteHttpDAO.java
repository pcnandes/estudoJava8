package java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class TesteHttpDAO {

	public void testarConexaoHttp() throws IOException, InterruptedException, URISyntaxException {
		/* HTTP 1.1
		URL url = new  URL("http://www.google.com.br");
		URLConnection urlConnection = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close(); */
		
		// HTTP2 Client
		URI uri = new URI("http://www.google.com.br");
		// HttpClient client = HttpClient.newHttpClient();
		// no HTTP2 os redirects nao vem configurados por default
		HttpClient client = HttpClient.newBuilder()
				.followRedirects(HttpClient.Redirect.ALWAYS)
				.build();
		HttpRequest req = HttpRequest.newBuilder(uri).GET().build();
		HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
		System.out.println(resp.body());
	}
}
