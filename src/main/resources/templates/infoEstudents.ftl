<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Práctica 2</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<div class="panel panel-default">
    <div class="panel-heading">${Titulo}</div>
    <div class="panel-body"><ul class="list-group">
        <li class="list-group-item">Matrícula: ${Student.matricula?string["0"]}</li>
        <li class="list-group-item">Nombre: ${Student.name} ${Student.lastname}</li>
        <li class="list-group-item">Teléfono: ${Student.tel}</li>
    </ul>
        <p>

        </p>

    <a href="/listStudents/" class="btn btn-primary"  role="button">Students List</a>
</div>


</body>
</html>