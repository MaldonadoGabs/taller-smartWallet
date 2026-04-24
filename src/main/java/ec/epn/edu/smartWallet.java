package ec.epn.edu;

public class smartWallet {
    private double balance;
    private String tipoUsuario;
    private boolean esActivo;

    public smartWallet(String tipoUsuario) {
        this.balance = 0;
        this.tipoUsuario = tipoUsuario;
        this.esActivo = true;
    }

    public boolean deposit(double monto) {
        if (monto <= 0) {
            return false;
        }

        double totalDeposito = monto;

        if (monto > 100) {
            totalDeposito = monto * 1.01;
        }

        if ("Standard".equals(tipoUsuario)) {
            if (balance + totalDeposito > 5000) {
                return false;
            }
        }

        balance += totalDeposito;
        return true;
    }

    public boolean withdraw(double monto) {
        // No se pueden retirar montos negativos o cero
        if (monto <= 0) {
            return false;
        }

        // No se puede retirar más de lo que hay en el saldo
        if (monto > balance) {
            return false;
        }

        // Realizar el retiro
        balance -= monto;

        // Si el saldo queda en exactamente 0, marcar la cuenta como "Inactiva"
        if (balance == 0) {
            esActivo = false;
        }

        return true;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isEsActivo() {
        return esActivo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
