package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while (opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println("Saldo da conta: " + banco.saldo(conta));
                    break;
                }
                case 2: {
                    System.out.println("Quantidade de contas: " + banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldo = entrada.nextDouble();
                    Conta novaConta = banco.cadastrarConta(numero, saldo);
                    System.out.println("Conta cadastrada: " + novaConta.getNumero());
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    Conta conta = banco.pesquisarConta(numero);
                    if (conta != null) {
                        System.out.println("Conta encontrada:");
                        System.out.println("Número: " + conta.getNumero());
                        System.out.println("Saldo: " + conta.getSaldo());
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    Conta contaRemovida = banco.removerConta(numero);
                    if (contaRemovida != null) {
                        System.out.println("Conta removida:");
                        System.out.println("Número: " + contaRemovida.getNumero());
                        System.out.println("Saldo: " + contaRemovida.getSaldo());
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("Samuel de Morais Lima");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
        System.out.println("Escolha uma opção:");
    }

}
