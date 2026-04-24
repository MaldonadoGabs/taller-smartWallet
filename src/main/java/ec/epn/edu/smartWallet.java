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

    public boolean depositar(double monto) {
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

    public boolean retirar(double monto) {
        if (monto <= 0) {
            return false;
        }

        if (monto > balance) {
            return false;
        }

        balance -= monto;

        if (balance == 0) {
            esActivo = false;
        }

        return true;
    }

    public double obtenerSaldo() {
        return balance;
    }

    public boolean estaActivo() {
        return esActivo;
    }

    public String obtenerTipoUsuario() {
        return tipoUsuario;
    }
}
