package ec.epn.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class smartWalletTest {
    private smartWallet wallet;

    @BeforeEach
    public void setUp() {
        wallet = new smartWallet("Standard");
    }


    @Test
    public void testDepositoValido() {
        boolean resultado = wallet.depositar(50);
        assertTrue(resultado, "El depósito de $50 debe ser exitoso");
        assertEquals(50.0, wallet.obtenerSaldo(), 0.01, "El saldo debe ser $50");
    }

    @Test
    public void testDepositoConChashback() {
        boolean resultado = wallet.depositar(200);
        assertTrue(resultado, "El depósito de $200 debe ser exitoso");
        assertEquals(202.0, wallet.obtenerSaldo(), 0.01, "El saldo debe ser $202 (200 + 1% cashback)");
    }

    @Test
    public void testDepositar100() {
        boolean resultado = wallet.depositar(100);
        assertTrue(resultado, "El depósito de $100 debe ser exitoso");
        assertEquals(100.0, wallet.obtenerSaldo(), 0.01, "El saldo debe ser $100");
    }

    @Test
    public void testDepositoExcedeLimite() {
        boolean resultado = wallet.depositar(6000);
        assertFalse(resultado, "El depósito de $6,000 debe fallar para usuario Standard");
        assertEquals(0.0, wallet.obtenerSaldo(), 0.01, "El saldo debe permanecer en $0");
    }

    @Test
    public void testDepositoNegativo() {
        boolean resultado = wallet.depositar(-50);
        assertFalse(resultado, "El depósito negativo debe fallar");
        assertEquals(0.0, wallet.obtenerSaldo(), 0.01, "El saldo debe permanecer en $0");
    }

    @Test
    public void testDepositarCero() {
        boolean resultado = wallet.depositar(0);
        assertFalse(resultado, "El depósito de $0 debe fallar");
        assertEquals(0.0, wallet.obtenerSaldo(), 0.01, "El saldo debe permanecer en $0");
    }

    @Test
    public void testRetiroValido() {
        wallet.depositar(100);
        boolean resultado = wallet.retirar(30);
        assertTrue(resultado, "El retiro de $30 debe ser exitoso");
        assertEquals(70.0, wallet.obtenerSaldo(), 0.01, "El saldo debe ser $70");
        assertTrue(wallet.estaActivo(), "La cuenta debe seguir activa");
    }

    @Test
    public void testRetirarTodo() {
        wallet.depositar(100);
        boolean resultado = wallet.retirar(100);
        assertTrue(resultado, "El retiro de $100 debe ser exitoso");
        assertEquals(0.0, wallet.obtenerSaldo(), 0.01, "El saldo debe ser $0");
        assertFalse(wallet.estaActivo(), "La cuenta debe estar inactiva cuando el saldo es $0");
    }

    @Test
    public void testRetiroSuperiorAlSaldo() {
        wallet.depositar(50);
        boolean resultado = wallet.retirar(100);
        assertFalse(resultado, "El retiro de $100 debe fallar con saldo de $50");
        assertEquals(50.0, wallet.obtenerSaldo(), 0.01, "El saldo debe permanecer en $50");
        assertTrue(wallet.estaActivo(), "La cuenta debe seguir activa");
    }

    @Test
    public void testRetiroNegativo() {
        wallet.depositar(100);
        boolean resultado = wallet.retirar(-50);
        assertFalse(resultado, "El retiro negativo debe fallar");
        assertEquals(100.0, wallet.obtenerSaldo(), 0.01, "El saldo debe permanecer en $100");
    }
}

