package br.com.fwinternetbanking.model;


public class Conta extends ContaAbstrata {
	
	

    // CONSTRUTORES
    public Conta(String numero, double saldo, Cliente cliente, TipoConta tipo) {
        super(numero, saldo, cliente, tipo);
    }

    @Override
    public void debitar(double valor) {
        if (getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
        } else {
            System.out.println("Saldo insuficiente");	// TODO forma tempor�ria de avisar erro
        }
    }
}
