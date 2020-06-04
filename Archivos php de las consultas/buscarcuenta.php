<?php
$con = mysqli_connect("192.168.1.241","ibai","Ibai@noa14","requeteguau");


$correo = $_GET['correo'];

$sql = "SELECT usuarios.nombre, usuarios.correo, usuarios.telefono, usuarios.contrasena, datos_mascota.nombre_mascota, datos_mascota.peso_mascota,datos_mascota.raza_mascota, datos_mascota.genero_mascota
		FROM usuarios, datos_mascota WHERE usuarios.correo like '$correo' AND datos_mascota.correo like '$correo'";  

$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){



	array_push($result,array(
    	'nombre'=>$row[0],
    	'correo'=>$row[1],
    	'telefono'=>$row[2],
    	'contrasena'=>$row[3],
    	'nombre_mascota'=>$row[4],
		'peso_mascota'=>$row[5],
		'raza_mascota'=>$row[6],
		'genero_mascota'=>$row[7],
	));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);

?>