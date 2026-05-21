package Portfolio02;

public class ValidadorCompliance {

    private static final double LIMITE_ALERTA = 50000.00;

    public static String validar(Transacao t) {
        // Usando Pattern Matching for switch (Java 21+)
        return switch (t) {
            case Aporte a -> (a.valor() > LIMITE_ALERTA)
                    ? "⚠️ ALERTA: Aporte de alto valor em análise de compliance: " + a.valor()
                    : "✅ Aporte aprovado automaticamente.";

            case Resgate r -> "🔍 Resgate de " + r.valor() + " enviado para auditoria manual.";

            case Transferencia tr -> "✅ Transferência de " + tr.valor() + " validada com sucesso.";
        };
    }
}