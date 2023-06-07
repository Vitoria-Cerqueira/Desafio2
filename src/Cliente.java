import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cliente {

     private String nomeCliente;
     private String email;
     private String cpf;

     private Map<String, Cliente> clientes = new HashMap<>();
     public static Scanner teclado = new Scanner(System.in);

     public Cliente(){

     }

    public Cliente(String nomeCliente, String email,String cpf, Map<String,Cliente> clientes){
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.cpf = cpf;
        this.clientes = clientes;
    }
    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public void menuCliente(){
        int opcao = 0;
        ClienteService clienteService = new ClienteService();
        while (opcao !=4){
            System.out.println("O que você deseja? \n1- Cadastrar \n2- Listar clientes cadastrados \n3- Voltar para menu o principal ");
            switch (teclado.nextInt()){
                case 1:
                    teclado.nextLine();
                    clienteService.cadastrarCliente(this);
                    break;
                    // o this referencia a propria classe
                case 2:
                   clienteService.listarClientesCadastrados(this.getClientes());
                    break;
                case 3:
                    opcao =4;
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    @Override
    public String toString() {
        return "Cliente: " + nomeCliente + ", " +
                " email: " + email + ", " +
                " cpf: " + cpf + ";";
    }
}
