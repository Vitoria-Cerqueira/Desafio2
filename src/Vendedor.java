import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Vendedor {
    private String nomeVedendor;
    private String email;
    private String senha;
    private String cpf;

    private Map<String, Vendedor> vendedores = new HashMap<>();
    public static Scanner teclado = new Scanner(System.in);

    public Vendedor(){

    }

    public Vendedor(String nomeCliente, String email,String senha,String cpf, Map<String, Vendedor> vendedores){
        this.nomeVedendor = nomeCliente;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.vendedores = vendedores;
    }
    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public Map<String, Vendedor> getVendedores() {
        return vendedores;
    }

    public void menuVendedor(){
        int opcao = 0;
        VendedorService vendedor = new VendedorService();
        while (opcao !=4){
            System.out.println("O que você deseja? \n1- Cadastrar vendedor\n2- Lista de vendedores cadastrado \n3- Voltar ao menu principal");
            switch (teclado.nextInt()){
                case 1:
                    teclado.nextLine();
                    vendedor.cadastrarVendedores(this);
                    break;

                case 2:
                    vendedor.listarVendedoresCadastrados(this.getVendedores());
                    break;
                case 3:
                    opcao = 4;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    @Override
    public String toString() {
        return "Vendedor: " + nomeVedendor + ", " +
                "email: " + email + ", " +
                "cpf: " + cpf;
    }
}
