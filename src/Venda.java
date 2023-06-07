import exception.RecursoNaoEncontradoException;

import java.time.LocalDate;
import java.util.*;

public class Venda {

    private int codigo;
    private String nomeProduto;
    private double preco;
    private int quantidade;
    private double valorTotal;
    private LocalDate dataRegistro;
    private Cliente cliente;
    private Vendedor vendedor;
    private VendedorService vendedors = new VendedorService();
    private ClienteService clients = new ClienteService();
    List<Venda> listaVendas = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    public Venda() {

    }

    public Venda(int codigo, String nomeProduto, double preco, int quantidade, double valorTotal, LocalDate dataRegistro, Cliente cliente, Vendedor vendedor) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataRegistro = dataRegistro;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {

        return "Venda: \n" +
                "Codigo: " + codigo +
                "\nNome do livro: " + nomeProduto +
                "\nPreco: " + preco +
                "\nQuantidade: " + quantidade +
                "\nData de registro: " + dataRegistro +
                "\nValorTotal: " + valorTotal +
                "\n" + cliente +
                "\n" +  vendedor +
                "\n----------------------------------------------------------";
    }
    public void cadastrarVenda(Map<String, Vendedor> vendedores, Map<String, Cliente> clientes) {
        System.out.println("==== Cadastro de venda ====");

        System.out.println("Informe o email do vendedor: ");
        String emailVendedor = teclado.next();

        vendedor = vendedors.buscarVendedorPorEmail(vendedores, emailVendedor);

        System.out.println("Informe o CPF do cliente: ");
        String cpfCliente = teclado.next();

        cliente = clients.buscarClientePorCpf(clientes, cpfCliente);

        System.out.println("Informe o código do livro: ");

        String codigo = teclado.next();
        int cod = Integer.parseInt(codigo);
        teclado.nextLine();

        System.out.println("Informe o nome do livro: ");
        String nomeProduto = teclado.next();

        System.out.println("Informe a data de registro (formato: AAAA-MM-DD)");
        String data = teclado.next();
       LocalDate dataRegistro = LocalDate.parse(data);

        System.out.println("Informe o preço unitário do livro: ");
        String preco = teclado.next();
        double preco2 = Double.parseDouble(preco);

        System.out.println("Informe a quantidade: ");
        String quantidade = teclado.next();
        int quantidade2 = Integer.parseInt(quantidade);

        valorTotal = quantidade2 * preco2;

        Venda venda = new Venda(cod, nomeProduto, preco2, quantidade2, valorTotal, dataRegistro, cliente, vendedor);

        listaVendas.add(venda);

        System.out.println("Venda cadastrada com sucesso.");
    }

    public void listarVenda(){

        if (!listaVendas.isEmpty()){
            listaVendas.forEach(System.out::println);
        }
        else {
            System.out.println("Nenhuma venda cadastrada");
        }
    }
    public Venda buscarVendasPorEmailVendedor(){
        System.out.println("Informe o email do vendedor: ");
        String emailVendedor = teclado.next();

        for (Venda venda: listaVendas) {
            if (venda.vendedor.getEmail().equals(emailVendedor)){
                System.out.println(listaVendas);
                return venda;
            }
        }
        throw new RecursoNaoEncontradoException("Não possui vendas casdastradas para esse email!");
    }

    public Venda buscarVendaPorCpfCliente(){
        System.out.println("Informe o CPF do cliente: ");
        String cpfCliente = teclado.next();
        for (Venda venda: listaVendas) {
            if (venda.cliente.getCpf().equals(cpfCliente)){
                System.out.println(listaVendas);
                return venda;
            }
        }
        throw new RecursoNaoEncontradoException("Não possui vendas cadastradas para esse cpf");
    }



}



