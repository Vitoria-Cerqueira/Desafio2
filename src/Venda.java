import exception.RecursoNaoEncontradoException;

import java.time.LocalDate;
import java.util.*;

public class Venda {
// criei a classe venda com todos os atributos para venda e com os metodos
    private int codigo;
    private String nomeProduto;
    private double preco;
    private int quantidade;
    private double valorTotal;
    private LocalDate dataRegistro;
    private Cliente cliente; // aqui estou fazendo a composiçao da classe cliente para classe venda
    private Vendedor vendedor; // aqui fiz a mesma coisa, fiz a composition
    private VendedorService vendedors = new VendedorService(); // instanciei minha classe vendedorService
    private ClienteService clients = new ClienteService(); // aqui também
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
        // fiz isso para que quando eu pedisse pra listar ele não colocar só a referencia da memória
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

        loginVendedor(vendedores);

        String cpfCliente = entradaDados("Informe o cpf do cliente: ").next();
        // chamei a composição também e o nome do objeto instanciado para poder usar o metodo que está dentro da classe clienteService
        cliente = clients.buscarClientePorCpf(clientes, cpfCliente);


        System.out.println("Informe o código do livro: ");
        // aqui coloquei do tipo string e passe para inteiro oara poder tratar qualquer erro que pudesse, detro do catch
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
        // aqui instanciei Venda para poder atribuir minhas variaveis aos atributos construidos no construtor
        Venda venda = new Venda(cod, nomeProduto, preco2, quantidade2, valorTotal, dataRegistro, cliente, vendedor);
        // aqui adicionei tudo aqui esta dentro do meu objeto venda para listaProdutos
        listaVendas.add(venda);

        System.out.println("Venda cadastrada com sucesso.");
    }

    public void listarVenda(){
    // esses dois pontos indica o metodo, ao inves de colocar o x, vc referencia o metodo
        // aqui fiz o if dizendo que se listaVendas tiver diferente de fazia, é para ele passar por essa lista com forEach e mostrar oq tem dentro
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
        // aqui estou querendo que meu metodo retorne um objeto, ele vai passar pelo Map de vendedor pegar o email e ve de o email digitado é igual e se for igual ele vai retornar a lista de venda e senão ele vai infromar que não existe vendedor cadastrado
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
    public Vendedor loginVendedor(Map<String, Vendedor> vendedor){
        System.out.println("Informe seu email: ");
        String email = teclado.next();
        Vendedor vendedorEncontrado = vendedors.buscarVendedorPorEmail(vendedor, email);

        if (verificarSenha(vendedorEncontrado)){
            System.out.println("Login feito com sucesso! ");
            return vendedorEncontrado;
        }
        throw new RecursoNaoEncontradoException("Senha invalida");
    }
    public boolean verificarSenha(Vendedor vendedorEncontrado){
        System.out.println("Informe sua senha: ");
        String senha = Base64.getEncoder().encodeToString(teclado.next().getBytes());
        if (!senha.equals(vendedorEncontrado.getSenha())){
            System.out.println("Senha invalida");
           int entrada = entradaDados("Quer tentar novamente \n1- Sim \n2- Não").nextInt();
           if (entrada == 1){
               verificarSenha(vendedorEncontrado);
           }else {
               return false;
           }
        }
        return true;
    }

    private Scanner entradaDados(String mensagemUsuario){
        System.out.println(mensagemUsuario);
        return new Scanner(System.in);
    }



}



