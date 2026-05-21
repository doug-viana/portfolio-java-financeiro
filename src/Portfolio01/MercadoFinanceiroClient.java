package Portfolio01;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MercadoFinanceiroClient {

    // Cliente HTTP reaproveitável
    private static final HttpClient client = HttpClient.newHttpClient();

    public static AtivoFinanceiro buscarCotacao(String parMoeda) {
        String url = "https://economia.awesomeapi.com.br/last/" + parMoeda;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Dispara a requisição para a internet
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            // Extração manual cirúrgica do JSON
            String nome = extrairValorJson(json, "\"name\":");
            double preco = Double.parseDouble(extrairValorJson(json, "\"bid\":"));
            String dataHora = extrairValorJson(json, "\"create_date\":");

            return new AtivoFinanceiro(parMoeda, nome, preco, dataHora);

        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar cotação de " + parMoeda + ": " + e.getMessage());
            return new AtivoFinanceiro(parMoeda, "Indisponível", 0.0, "N/A");
        }
    }

    // Método utilitário para "recortar" o valor exato de dentro do texto JSON
    private static String extrairValorJson(String json, String chave) {
        try {
            int inicioChave = json.indexOf(chave) + chave.length();
            int inicioValor = json.indexOf("\"", inicioChave) + 1;
            int fimValor = json.indexOf("\"", inicioValor);
            return json.substring(inicioValor, fimValor);
        } catch (Exception e) {
            return "0.0";
        }
    }
}