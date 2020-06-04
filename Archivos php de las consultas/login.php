<?php 
		$con=mysqli_connect("192.168.1.241","ibai","Ibai@noa14","requeteguau");
	
		$correo = $_POST["correo"];
		$contrasena = $_POST["contrasena"];

		$sql = "SELECT * FROM usuarios WHERE  correo = '$correo' AND contrasena = '$contrasena'";
		$result = mysqli_query($con,$sql);
		
		if($result->num_rows > 0){
			echo "Sesion Iniciada" ;
		}else{
  			 echo "user not found";
}
?>