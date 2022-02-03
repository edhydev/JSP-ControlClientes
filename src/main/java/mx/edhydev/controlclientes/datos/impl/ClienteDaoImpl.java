package mx.edhydev.controlclientes.datos.impl;

import mx.edhydev.controlclientes.datos.ClienteDao;
import mx.edhydev.controlclientes.datos.Conexion;
import mx.edhydev.controlclientes.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {
    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellidos, email, telefono, saldo FROM cliente";
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellidos, email, telefono, saldo FROM cliente WHERE id_cliente = ?";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellidos, email, telefono, saldo) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre = ?, apellidos = ?, email = ?, telefono = ?, saldo = ? WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";

    @Override
    public List<Cliente> listar() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_SELECT);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellidos(resultSet.getString("apellidos"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setSaldo(resultSet.getDouble("saldo"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (resultSet != null) {
                Conexion.close(resultSet);
            }
            if (statement != null) {
                Conexion.close(statement);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return clientes;
    }

    @Override
    public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, cliente.getIdCliente());
            resultSet = statement.executeQuery();
            resultSet.absolute(1);

            cliente.setNombre(resultSet.getString("nombre"));
            cliente.setApellidos(resultSet.getString("apellidos"));
            cliente.setEmail(resultSet.getString("email"));
            cliente.setTelefono(resultSet.getString("telefono"));
            cliente.setSaldo(resultSet.getDouble("saldo"));

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (resultSet != null) {
                Conexion.close(resultSet);
            }
            if (statement != null) {
                Conexion.close(statement);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return cliente;
    }

    @Override
    public int insertar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registrosModificados = 0;

        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_INSERT);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());
            statement.setDouble(5, cliente.getSaldo());

            registrosModificados = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (statement != null) {
                Conexion.close(statement);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return registrosModificados;
    }

    @Override
    public int actualizar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registrosModificados = 0;

        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_UPDATE);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());
            statement.setDouble(5, cliente.getSaldo());
            statement.setInt(6, cliente.getIdCliente());

            registrosModificados = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (statement != null) {
                Conexion.close(statement);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return registrosModificados;
    }

    @Override
    public int eliminar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registrosEliminados = 0;

        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_DELETE);
            statement.setInt(1, cliente.getIdCliente());
            registrosEliminados = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (statement != null) {
                Conexion.close(statement);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }
        return registrosEliminados;
    }
}
