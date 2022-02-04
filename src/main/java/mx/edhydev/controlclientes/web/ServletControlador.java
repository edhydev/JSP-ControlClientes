package mx.edhydev.controlclientes.web;

import mx.edhydev.controlclientes.datos.ClienteDao;
import mx.edhydev.controlclientes.datos.impl.ClienteDaoImpl;
import mx.edhydev.controlclientes.dominio.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDao clienteDao = new ClienteDaoImpl();
        List<Cliente> clientes = clienteDao.listar();
        System.out.println("clientes: " + clientes);
        HttpSession session = request.getSession();
        session.setAttribute("clientes", clientes);
        session.setAttribute("totalClientes", clientes.size());
        session.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        // request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos el idCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        ClienteDao clienteDao = new ClienteDaoImpl();
        Cliente cliente = clienteDao.encontrar(new Cliente(idCliente));
        System.out.println("Cliente encontrado: " + cliente);
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !saldoString.equals("")) {
            saldo = Double.parseDouble(saldoString);
        }

        // Creamos el objeto del cliente
        Cliente cliente = new Cliente(nombre, apellidos, email, telefono, saldo);

        int registrosModificados = new ClienteDaoImpl().insertar(cliente);
        System.out.println("Registros insertados: " + registrosModificados);

        // Redirigimos hacia acción por default
        this.accionDefault(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !saldoString.equals("")) {
            saldo = Double.parseDouble(saldoString);
        }

        // Creamos el objeto del cliente
        Cliente cliente = new Cliente(idCliente, nombre, apellidos, email, telefono, saldo);

        int registrosModificados = new ClienteDaoImpl().actualizar(cliente);
        System.out.println("Registros actualizados: " + registrosModificados);
        // Redirigimos hacia acción por default
        this.accionDefault(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        // Creamos el objeto del cliente
        Cliente cliente = new Cliente(idCliente);

        int registrosModificados = new ClienteDaoImpl().eliminar(cliente);
        System.out.println("Registros eliminados: " + registrosModificados);

        // Redirigimos hacia acción por default
        this.accionDefault(request, response);
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
}
