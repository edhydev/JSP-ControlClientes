<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <!--Inicio de la Lista de clientes -->
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Nombre</th>
                            <th>Saldo</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Iteramos cada elemento de la lista de clientes -->
                        <c:forEach var="cliente" items="${clientes}">
                            <tr>
                                <td>${cliente.idCliente}</td>
                                <td>${cliente.nombre} ${cliente.apellidos}</td>
                                <td><fmt:formatNumber value="${cliente.saldo}" type="currency"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                       class="btn btn-secondary btn-sm"><i class="fas fa-angle-double-right"></i> Editar</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--Fin de la Lista de clientes -->

            <!-- Inicio Tarjetas para los totales -->
            <div class="col-md-3">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Saldo Total</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${saldoTotal}" type="currency"/>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-4"><i class="fas fa-users"></i> ${totalClientes}</h4>
                    </div>
                </div>
            </div>
            <!---->
        </div>
    </div>
</section>