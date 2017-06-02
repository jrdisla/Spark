<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Práctica 2</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<div class="panel panel-danger">
    <div class="panel-heading">${Titulo}</div>
    <div class="panel-body">
        <form action="/individualInfo/${Student.matricula?string["0"]}/" method="post">


        <div class="input-group">
            Nombre: <input name="nombre" type="text" class="form-control" value="${Student.name}" placeholder="Nombre Estudiante" aria-describedby="basic-addon1"><br/>
        </div>
        <div class="input-group">
            Apellido: <input name="apellido" type="text" class="form-control" value="${Student.lastname}" placeholder="Apellido Estudiante" aria-describedby="basic-addon1"><br/>
        </div>
        <div class="input-group">
            Telefono: <input name="telefono" type="text" class="form-control" value="${Student.tel}" placeholder="Telefono Estudiante" aria-describedby="basic-addon1"><br/>
        </div>
        <p>

        </p>
        <button type="submit" class="btn btn-warning">Actualizar</button>
    </form></div>
</div>



</body>
</html>