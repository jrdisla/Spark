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

<div class="panel panel-primary">
    <div class="panel-heading">${Titulo}</div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li class="active"><a href="/addStudent/">Add Student</a></li>
            <li><a href="/listStudents/">List Students</a></li>

        </ul>
        <form action="/listStudents/" method="post">
            <div class="input-group" margin="auto">
                Matricula: <input name="matricula" type="text" class="form-control" placeholder="Matricula Estudiante" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
                Nombre: <input name="nombre" type="text" class="form-control" placeholder="Nombre Estudiante" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
                Apellido: <input name="apellido" type="text" class="form-control" placeholder="Apellido Estudiante" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
                Telefono: <input name="telefono" type="text" class="form-control" placeholder="Telefono Estudiante" aria-describedby="basic-addon1">

            </div>
<p>

</p>
            <button type="submit" class="btn btn-primary">Agregar</button>

        </form>
    </div>
</div>


</body>
</html>