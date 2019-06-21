<?php
	require 'connection.php';
	session_start();
	if($_SERVER['REQUEST_METHOD']=='POST'&&isset($_POST['email'])){
		$email = $_POST['email'];
		$pass = md5($_POST['password']);
		if(isset($_REQUEST['id'])){
			$query = "UPDATE `teacher_table` SET `password`='$pass' WHERE email='$email'";
			if($run = mysqli_query($con, $query)){
				echo "Success !!";
			}
		}

	}
?>