<h1>Desafio 2</h1>
<hr>
O sistema apresentado simula um processo de vendas. Ele permite casdasrar vendas, clientes, vendedores e fazer consultas referenta as vendas.
<hr>
<h2>Requisitos: </h2>
<li>O sistema permite cadastrar diversas vendas no sistema;
<li>Para cadastrar uma venda será necessário:</li>
■ vendedor; <br>
■ cliente;<br>
■ código;<br>
■ nome do produto; <br>
■ preço; <br>
■ quantidade;<br>
■ valor total da compra.<br>

● O sistema não permite cadastrar vendas para clientes não cadastrados; <br>
● O sistema não permite cadastrar vendas de vendedores não cadastrados; <br>
● O sistema permite listar todas as vendas cadastradas; <br>
● O sistema permite listar todos os vendedores cadastrados; <br>
● O sistema permite listar todos os clientes cadastrados; <br>
● O sistema não permite cadastrar clientes com e-mail inválido (sem @); <br>
● O sistema não permite cadastrar vendedores com e-mail inválido (sem @); <br>
● O sistema não permite cadastrar clientes com CPFs repetidos; <br>
● O sistema não permite cadastrar vendedores com CPFs repetidos; <br>
● O sistema não permite cadastrar clientes com e-mails repetidos; <br>
● O sistema não permite cadastrar vendedores com e-mails repetidos; <br>
● O sistema permite pesquisar todas as compras de um cliente específico através de seu CPF; <br>
● O sistema permite pesquisar todas as vendas de um vendedor específico através de seu e-mail; <br>
● O sistema deverá permitir que o usuário saia do sistema; <br>
● O sistema deverá exibir um menu de acesso ao usuário com todas as opções disponíveis. <br>

<h2>Sistema de livros: </h2>
- Criei uma classe Vendedor e outra VendedorService, dentro de vendedor tem os atributos, os getters, o construtor, e fiz um método menuVendedor e no vendedorService fiz a composição da classe vendedor e coloquei os métodos. <br>
- Criei uma classe Cliente e outra ClienteService, no cliente coloquei os atributos, os getters, o construtor e fiz um método menuCliente e no clienteService fiz a composição da classe cliente e coloquei os métodos. <br>
- Criei uma classe SistemaRegistro, fiz o método menuPrincipal, usei o try e o catch e tratei algumas exception, e usei o while também. <br>
- Criei uma classe Venda com todos os atrubutos, fiz a composição da classe Vnededor e da classe Cliente, e instanciei as classes VendedorService e ClienteService, e dentro da classe Venda implemenite os métodos de listarVendas, procurar por vendas, entre outras funcionalidades.
- Criei uma classe RecursoNaoEncontradoException para poder modificar e tratar essas exception.