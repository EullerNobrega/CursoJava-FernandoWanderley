/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fwinternetbanking;

/**
 *
 * @author Euller
 */
public class Conta extends ContaAbstrata {

    public Conta(double saldo, String numero) {
        super(saldo, numero);
    }

    @Override
    public void debitar(double valor) {
        if( saldo < valor){
            System.out.println("Saldo insuficiente");
        }else{
            this.saldo -= valor;
        }
    }
}
