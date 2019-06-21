<?php
	require 'connection.php';
	
	$email = $_REQUEST['email'];
	$pass = md5($_REQUEST['password']);
	
	$result = array();
	
	$query = "select password, user_id, type, name from teacher_table where email='$email'";
	if($run = mysqli_query($con, $query)){
		$item = mysqli_fetch_array($run);
		if($item[0]==$pass){
			array_push($result,"".$item[1]);
			array_push($result,"".$item[2]);
			array_push($result,"success");
			array_push($result,"".$item[3]);
			echo json_encode($result);
		}
		else{
			array_push($result,"failed");
			echo json_encode($result);
		}
	}
?>