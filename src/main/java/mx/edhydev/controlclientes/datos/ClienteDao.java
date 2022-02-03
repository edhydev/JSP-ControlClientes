package mx.edhydev.controlclientes.datos;

import mx.edhydev.controlclientes.dominio.Cliente;

import java.util.List;

public interface ClienteDao {
    List<Cliente> listar();

    Cliente encontrar(Cliente cliente);

    int insertar(Cliente cliente);

    int actualizar(Cliente cliente);

    int eliminar(Cliente cliente);
}
