package mx.edhydev.controlclientes.web;

import mx.edhydev.controlclientes.datos.ClienteDao;
import mx.edhydev.controlclientes.datos.impl.ClienteDaoImpl;
import mx.edhydev.controlclientes.dominio.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDao clienteDao = new ClienteDaoImpl();
        List<Cliente> clientes = clienteDao.listar();
        System.out.println("clientes: " + clientes);
        request.setAttribute("clientes", clientes);
        request.setAttribute("totalClientes", clientes.size());
        request.setAttribute("saldoTotal", calcularSaldoTotal(clientes));
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
}
