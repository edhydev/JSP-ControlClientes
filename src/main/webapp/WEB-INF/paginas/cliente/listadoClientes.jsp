<%--
  Created by IntelliJ IDEA.
  User: edgar
  Date: 03/02/22
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
    <c:forEach var="cliente" items="${clientes}">
        <li>${cliente.idCliente} | ${cliente.nombre} ${cliente.apellidos} | ${cliente.saldo}</li>
    </c:forEach>
</ul>
