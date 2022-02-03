<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/35704829a4.js" crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>

<!-- Cabecero  -->
<jsp:include page="WEB-INF/paginas/comunes/cabecero.jsp"/>

<!-- Botones de navegación  -->
<jsp:include page="WEB-INF/paginas/comunes/botonesNavegacion.jsp"/>

<!-- Listado de clientes -->
<jsp:include page="WEB-INF/paginas/cliente/listadoClientes.jsp"/>

<!-- Pie de Pagina  -->
<jsp:include page="WEB-INF/paginas/comunes/piePagina.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>

</html>