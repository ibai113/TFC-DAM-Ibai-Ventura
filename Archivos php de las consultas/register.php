<?php 

	#con=mysqli_connect("ip_servidor","correo","contraseña","base_datos")	

	$con=mysqli_connect("192.168.1.241","ibai","Ibai@noa14","requeteguau");
	$con2=mysqli_connect("192.168.1.241","ibai","Ibai@noa14","requeteguau");
	
	$nombre = $_POST["nombre"];
	$correo = $_POST["correo"];
	$telefono = $_POST["telefono"];
	$contrasena =$_POST["contrasena"];
	$nombre_mascota =$_POST["nombre_mascota"];
	$peso_mascota =$_POST["peso_mascota"];
	$raza_mascota =$_POST["raza_mascota"];
	$genero_mascota =$_POST["genero_mascota"];

	$sql = "INSERT INTO usuarios(nombre,correo,telefono,contrasena) VALUES ('$nombre','$correo','$telefono','$contrasena')";
	$sql2 = "INSERT INTO datos_mascota(nombre_mascota,peso_mascota,raza_mascota,genero_mascota,correo) VALUES ('$nombre_mascota','$peso_mascota','$raza_mascota','$genero_mascota','$correo')";

	$result = mysqli_query( $con,$sql );
	$result2 = mysqli_query( $con2,$sql2);
	
	if($result) {
		echo "Registro guardado";
	}
	else {
		echo "Error, correo existente";
	}

?>