import exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    Cliente cliente = new Cliente();
    Map<String, Cliente> clientes = new HashMap<>();
    @InjectMocks
    ClienteService clienteService = new ClienteService();

    @BeforeEach
    public void setup() {
        cliente = new Cliente("Vitoria", "vitoria@gmail.com", "123456", clientes);
    }

    @Test
    public void testarCadastroDoCliente() {
//        assertTrue(teclado.hasNext());
        clienteService.cadastrarCliente(cliente);
    }

    @Test
    public void testarEmailValido() {
        String email = "vitoria@gmail.com";
        cliente = new Cliente("Vitoria", email, "123456", clientes);
        String validarEmail = clienteService.validarEmail(email, clientes);
        assertEquals(email, validarEmail);
    }

    @Test
    public void testarListaDeClienteVazia() {
        clientes.put(cliente.getCpf(), cliente);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        clienteService.listarClientesCadastrados(new HashMap<>());
        assertEquals("Nenhum cliente cadastrado", outputStream.toString().trim());
    }

    @Test
    public void testarListaDeClienteCadastrado() {
        clientes.put(cliente.getCpf(), cliente);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        clienteService.listarClientesCadastrados(clientes);
        assertEquals(clientes.values().toString().trim().replaceAll(("(\\[)|(\\])"), ""), outputStream.toString().trim());
    }

//    @Test
//    public void testarBuscarClientePorCpfVazio() {
//        clienteService.buscarClientePorCpf(new HashMap<>(), cliente.getCpf());
//        assertThrows(RecursoNaoEncontradoException.class, () -> clienteService.buscarClientePorCpf(new HashMap<>(), cliente.getCpf()));
//    }

    @Test
    public void testarBuscarClientePorCpfVazio() {
        RecursoNaoEncontradoException exception = assertThrows(
                RecursoNaoEncontradoException.class, () ->
                        clienteService.buscarClientePorCpf(new HashMap<>(), cliente.getCpf()));

        assertEquals("O cliente não está cadastrado",exception.getMessage());
    }

}
