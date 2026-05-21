package Portfolio02;

public sealed interface Transacao permits Aporte, Resgate, Transferencia {
    double valor();
    String origem();
}

record Aporte(double valor, String origem) implements Transacao {}
record Resgate(double valor, String origem) implements Transacao {}
record Transferencia(double valor, String origem, String destino) implements Transacao {}