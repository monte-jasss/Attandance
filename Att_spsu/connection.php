<?php
	$host = "localhost";
	$username = "root";
	$password = "";
	$database = "db_attendance";
	
	$con = mysqli_connect($host, $username, $password, $database);

	if(isset($_GET['internet'])){
		if(!$con){
			echo "false";
		} else {
			echo "true";
		}
	}
	
?>