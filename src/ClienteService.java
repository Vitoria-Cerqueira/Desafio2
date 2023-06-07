import exception.RecursoNaoEncontradoException;

import java.util.Map;
import java.util.Scanner;

public class ClienteService {
    Cliente clienteObjeto = new Cliente();
    Scanner teclado = new Scanner(System.in);

    public ClienteService(){

    }

    public void cadastrarCliente(Cliente cliente){
        System.out.println("============ Cadastro do Cliente =============");

        System.out.println("Digite seu nome: ");
        String nomeCliente = teclado.next();

        System.out.println("Digite seu E-mail: ");
        String email = validarEmail(teclado.next(), cliente.getClientes());


        System.out.println("Digite seu CPF: ");
        String cpf = teclado.next();


        if (!(cliente.getClientes().containsKey(cpf))){
            clienteObjeto = new Cliente(nomeCliente, email, cpf, cliente.getClientes());
            cliente.getClientes().put(cpf, clienteObjeto);
            System.out.println("Cliente cadastrado com sucesso");
        } else {
            System.out.println("CPF já cadastrados");
        }

    }

    public boolean verificarEmailExistente(Map<String, Cliente> clientes, String email){
        for (Cliente chave: clientes.values()) {
            if (chave.getEmail().contains(email)){
                return false;
            }
        }
        return true;
    }
     public String validarEmail(String email, Map<String, Cliente> clientes){
        String emailvalidado = email;
         if (!email.contains("@") || !verificarEmailExistente(clientes,email)){
             System.out.println("E-mail inválido");
             System.out.println("Digite um e-mail válido: ");
             emailvalidado = teclado.next();
             validarEmail(emailvalidado,clientes);
         }
         return emailvalidado;
     }

     public void listarClientesCadastrados(Map<String, Cliente> clientes){
        if (!clientes.isEmpty()){
            clientes.forEach((chave,valor) -> System.out.println(valor));
        }else {
            System.out.println("Nenhum cliente cadastrado");
        }

     }

    public Cliente buscarClientePorCpf(Map<String, Cliente> clientes,String cpf){
        for (Cliente cliente: clientes.values()) {
            if (cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        throw new RecursoNaoEncontradoException("O cliente não está cadastrado");
    }

}
