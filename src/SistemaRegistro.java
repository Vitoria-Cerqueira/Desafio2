import exception.RecursoNaoEncontradoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaRegistro {


    public static void mostrarMenuPrincipal() {
        Cliente cliente = new Cliente();
        Vendedor vendedor = new Vendedor();
        Venda venda = new Venda();
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;

        System.out.println("=============== Menu ===============");
        System.out.println("Bem-vindo a livraria da Vitória");

        while (opcao != 7) {
            try {
                System.out.println("O que você deseja? \n1- Cliente \n2- Vendedor \n3- Cadastrar Venda \n4- Listar vendas \n5- Buscar venda do Vendedor \n6- Buscar venda do Cliente \n7- Sair do programa");
                switch (teclado.nextInt()) {
                    case 1:
                        cliente.menuCliente();
                        break;

                    case 2:
                        vendedor.menuVendedor();
                        break;
                    case 3:
                        venda.cadastrarVenda(vendedor.getVendedores(), cliente.getClientes());
                        break;
                    case 4:
                        venda.listarVenda();
                        break;
                    case 5:
                        venda.buscarVendasPorEmailVendedor();
                        break;
                    case 6:
                        venda.buscarVendaPorCpfCliente();
                        break;
                    case 7:
                        opcao = 7;
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } catch (RecursoNaoEncontradoException err) {
                System.out.println(err.getMessage());
            }
            catch (NumberFormatException err2){
                System.out.println("Informe um numero inteiro");
            }
            catch (InputMismatchException errInput){
                System.out.println("Tipo invalido");
                teclado.nextLine();
            }

        }
        System.out.println("Obrigada! Volte sempre, programa encerrado.");
        System.exit(0);
    }

}

