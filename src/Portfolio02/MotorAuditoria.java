package Portfolio02;

import java.util.List;

public class MotorAuditoria {
    public static void main(String[] args) {
        System.out.println("=== 🛡️ ANBIMA COMPLIANCE: Sistema de Auditoria ===\n");

        List<Transacao> loteDiario = List.of(
                new Aporte(1000.0, "Cliente_A"),
                new Aporte(75000.0, "Cliente_B"), // Deve disparar alerta
                new Resgate(5000.0, "Cliente_C"),
                new Transferencia(10000.0, "Cliente_D", "Conta_Interna")
        );

        loteDiario.forEach(t -> {
            String status = ValidadorCompliance.validar(t);
            System.out.println("[AUDITORIA] " + status);
        });

        System.out.println("\n✅ Auditoria concluída com sucesso.");
    }
}