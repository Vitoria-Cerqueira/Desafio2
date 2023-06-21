import exception.RecursoNaoEncontradoException;

import java.util.Base64;
import java.util.Map;
import java.util.Scanner;

public class VendedorService {
    Vendedor vendedorObjeto = new Vendedor();
    Scanner teclado = new Scanner(System.in);

    public VendedorService(){

    }

    public void cadastrarVendedores(Vendedor vendedor){
        System.out.println("============ Cadastro do Vendedor =============");

        System.out.println("Digite seu nome: ");
        String nomeCliente = teclado.next();

        System.out.println("Digite seu E-mail: ");
        String email = validarEmail(teclado.next(), vendedor.getVendedores());

        System.out.println("Digite sua senha: ");
        String senha = Base64.getEncoder().encodeToString(teclado.next().getBytes());


        System.out.println("Digite seu CPF: ");
        String cpf = teclado.next();


        if (!(vendedor.getVendedores().containsKey(cpf))){
            vendedorObjeto = new Vendedor(nomeCliente, email,senha, cpf, vendedor.getVendedores());
            vendedor.getVendedores().put(cpf, vendedorObjeto);
            System.out.println("Vendedor cadastrado com sucesso");
        } else {
            System.out.println("CPF já cadastrados");
        }

    }

    public boolean verificarEmailExistente(Map<String, Vendedor> vendedores, String email){
        for (Vendedor chave: vendedores.values()) {
            if (chave.getEmail().contains(email)){
                return false;
            }
        }
        return true;
    }
    public String validarEmail(String email, Map<String, Vendedor> vendedores){
        String emailvalidado = email;
        if (!email.contains("@") || !verificarEmailExistente(vendedores,email)){
            System.out.println("E-mail inválido");
            System.out.println("Digite um e-mail válido: ");
            emailvalidado = teclado.next();
            validarEmail(emailvalidado,vendedores);
        }
        return emailvalidado;
    }

    public void listarVendedoresCadastrados(Map<String, Vendedor> vendedores){
        if (!vendedores.isEmpty()){
            vendedores.forEach((chave,valor) -> System.out.println(valor));
        }else {
            System.out.println("Nenhum vendedor cadastrado");
        }

    }

    public Vendedor buscarVendedorPorEmail(Map<String, Vendedor> vendedores,String email){
        for (Vendedor vendedor: vendedores.values()) {
            if (vendedor.getEmail().equals(email)){
                return vendedor;
            }
        }
        throw new RecursoNaoEncontradoException("O vendedor não está cadastrado");
    }






}
