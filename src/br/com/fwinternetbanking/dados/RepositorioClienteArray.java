/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fwinternetbanking.dados;

import br.com.fwinternetbanking.exceptions.ArrayCheioException;
import br.com.fwinternetbanking.exceptions.ClienteExisteException;
import br.com.fwinternetbanking.exceptions.ClienteNaoEncontradoException;
import br.com.fwinternetbanking.model.Cliente;
import br.com.fwinternetbanking.model.IRepCliente;

/**
 *
 * @author Euller
 */
public class RepositorioClienteArray implements IRepCliente {

	private Cliente[] clientes;
	private int indice;
	private final static int tamCache = 100;

	public RepositorioClienteArray() {
		indice = 0;
		clientes = new Cliente[tamCache];
	}

	public void inserir(Cliente c) throws Exception {
		try {
			if (existe(c.getCpf()) == false) {
				clientes[indice] = c;
				indice++;
			} else {
				throw new ClienteExisteException();
			}
		} catch (ArrayIndexOutOfBoundsException e) {

			throw new ArrayCheioException();
		}
	}

	private int procurarIndice(String cpf) {
		int i = 0;
		int ind = -1;

		if (clientes != null) {
			for (Cliente c : clientes) {

				if (c == null) {
					break;
				} else if (c.getCpf().equals(cpf)) {
					ind = i;
					break;
				}

				i++;
			}
		}
		return ind;
	}

	public boolean existe(String cpf) {

		boolean resp = false;
		int i = this.procurarIndice(cpf);
		if (i != -1) {
			resp = true;
		}
		return resp;
	}

	public void atualizar(Cliente c) throws Exception {

		int i = procurarIndice(c.getCpf());
		if (i != -1) {
			clientes[i] = c;
		} else {
			throw new ClienteNaoEncontradoException();
		}
	}

	public Cliente procurar(String cpf) throws Exception {
		Cliente c;

		if (existe(cpf)) {
			int i = this.procurarIndice(cpf);
			c = clientes[i];
			return c;
		} else {
			throw new ClienteNaoEncontradoException();
		}
	}

	public void remover(Cliente c) throws Exception {
		if (existe(c.getCpf())) {
			int i = this.procurarIndice(c.getCpf());
			clientes[i] = clientes[indice - 1];
			clientes[indice - 1] = null;
			indice = indice - 1;
		} else {
			throw new ClienteNaoEncontradoException();
		}
	}

}
