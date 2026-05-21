package Portfolio01;

import java.util.concurrent.CompletableFuture;

public class IngestionPipeline {
    public static void main(String[] args) {
        System.out.println("=== 📈 ANBIMA TEST: Pipeline de Ingestão de Ativos ===\n");
        System.out.println("[SISTEMA] Conectando à bolsa de valores e disparando threads...");

        long tempoInicio = System.currentTimeMillis();

        // 1. Disparamos as 3 requisições HTTP em paralelo!
        CompletableFuture<AtivoFinanceiro> taskDolar = CompletableFuture.supplyAsync(() ->
                MercadoFinanceiroClient.buscarCotacao("USD-BRL"));

        CompletableFuture<AtivoFinanceiro> taskEuro = CompletableFuture.supplyAsync(() ->
                MercadoFinanceiroClient.buscarCotacao("EUR-BRL"));

        CompletableFuture<AtivoFinanceiro> taskBitcoin = CompletableFuture.supplyAsync(() ->
                MercadoFinanceiroClient.buscarCotacao("BTC-BRL"));

        // 2. Aguardamos TODAS terminarem a viagem pela internet e voltarem com os dados
        AtivoFinanceiro dolar = taskDolar.join();
        AtivoFinanceiro euro = taskEuro.join();
        AtivoFinanceiro bitcoin = taskBitcoin.join();

        long tempoFim = System.currentTimeMillis();
        double tempoTotal = (tempoFim - tempoInicio) / 1000.0;

        // 3. Imprimimos o Relatório Consolidado formatado com Text Blocks
        System.out.println("\n--- 📊 Relatório Consolidado do Mercado ---");
        imprimirAtivo(dolar);
        imprimirAtivo(euro);
        imprimirAtivo(bitcoin);

        System.out.println("\n⏱️ Performance: 3 requisições de rede completadas em " + tempoTotal + " segundos.");
        System.out.println("✅ Arquitetura Assíncrona aprovada.");
    }

    private static void imprimirAtivo(AtivoFinanceiro ativo) {
        System.out.println("""
                > %s (%s)
                  Preço Atual: R$ %.2f
                  Última Atualização: %s
                """.formatted(ativo.nome(), ativo.codigo(), ativo.precoAtual(), ativo.dataConsulta()));
    }
}