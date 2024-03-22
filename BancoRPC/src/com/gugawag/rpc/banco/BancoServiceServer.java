package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Conta> contas;

    public BancoServiceServer() throws RemoteException {
        super();
        this.contas = new HashMap<>();
    }

    @Override
    public double saldo(String numero) throws RemoteException {
        if (contas.containsKey(numero)) {
            return contas.get(numero).getSaldo();
        }
        throw new RemoteException("Conta não encontrada.");
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public Conta cadastrarConta(String numero, double saldo) {
        Conta novaConta = new Conta(numero, saldo);
        contas.put(numero, novaConta);
        return novaConta;
    }

    @Override
    public Conta pesquisarConta(String numero) {
        if (contas.containsKey(numero)) {
            return contas.get(numero);
        }
        return null;
    }

    @Override
    public Conta removerConta(String numero) {
        if (contas.containsKey(numero)) {
            return contas.remove(numero);
        }
        return null;
    }



}
