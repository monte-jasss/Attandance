<?php
	require 'connection.php';
	
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$type = $_POST['type'];
		
		$file_name =  $_FILES["csv_file"]["name"];
		$file_tmp_name =  $_FILES["csv_file"]["tmp_name"];
		$file_type = strtolower(pathinfo($file_name,PATHINFO_EXTENSION));

		if($file_type == "csv") {
			$file = fopen($file_tmp_name, 'r');
			switch($type){
				case 'Student':
					uploadUser($con,$file);
					break;
				case 'Teacher':
					uploadNotice($con,$file);
					break;
				case 'Subject':
					uploadSubject($con,$file);
					break;
			}
		} else {
			echo "Upload file type error !";
		}
	} else {
		echo mysqli_error();
	}
	
	function uploadUser($con,$file){
		while(($data = fgetcsv($file, 1000, ',')) == TRUE){
			$enrollment = mysqli_real_escape_string($con, validate($data[0]));
			$name = mysqli_real_escape_string($con, validate($data[1]));
			$branch = mysqli_real_escape_string($con, validate($data[2]));
			$sem = mysqli_real_escape_string($con, validate($data[3]));
			$year = mysqli_real_escape_string($con, validate($data[4]));
			
			if($enrollment != 'Enrollment'){
				$query = "INSERT INTO `student_table`(`enrollment`, `name`, `branch`, `semester`, `year`) VALUES ('$enrollment', '$name', '$branch', '$sem', '$year')";
				mysqli_query($con, $query);
				if(mysqli_affected_rows($con) > 0){
					echo '<b style="color:green;">'.$enrollment.'</b><br>';
				} else {
					echo '<b style="color:red;">'.$enrollment.'</b><br>';
				}
			}
		}
		echo '<a href="upload-csv-from-here.php">Back</a><br>';
	}
	
	function uploadNotice($con,$file){
		while(($data = fgetcsv($file, 1000, ',')) == TRUE){
			$name = mysqli_real_escape_string($con, validate($data[0]));
			$email = mysqli_real_escape_string($con, validate($data[1]));
			$pass = mysqli_real_escape_string($con, validate($data[2]));
			$type = mysqli_real_escape_string($con, validate($data[3]));
			
			if($name != 'Name'){
				$query = "INSERT INTO `teacher_table`(`name`, `email`, `password`, `type`) VALUES ('$name', '$email','$pass', '$type')";
				mysqli_query($con, $query);
				if(mysqli_affected_rows($con) > 0){
					echo '<b style="color:green;">'.$name.'</b><br>';
				} else {
					echo '<b style="color:red;">'.$name.'</b><br>';
				}
			}
		}
		echo '<a href="upload-csv-from-here.php">Back</a><br>';
	}
	
	function uploadSubject($con,$file){
		while(($data = fgetcsv($file, 1000, ',')) == TRUE){
			$name = mysqli_real_escape_string($con, validate($data[0]));
			$code = mysqli_real_escape_string($con, validate($data[1]));
			$credit = mysqli_real_escape_string($con, validate($data[2]));
			
			if($name != 'Name'){
				$query = "INSERT INTO `subject_table`(`code`, `name`, `credit`) VALUES ('$code', '$name','$credit')";
				mysqli_query($con, $query);
				if(mysqli_affected_rows($con) > 0){
					echo '<b style="color:green;">'.$name.'</b><br>';
				} else {
					echo '<b style="color:red;">'.$name.'</b><br>';
				}
			}
		}
		echo '<a href="upload-csv-from-here.php">Back</a><br>';
	}
	
	/* function sendNotification($con, $title, $content){
		$path = 'https://fcm.googleapis.com/fcm/send';
		$s_key = 'AAAAeeKzABk:APA91bHozpeGMFqOiU2YhpygoZUwbUm5S7fwg2VzwjLRGBKJm_T64jjWWQWkc8zvtJWmJjGBxrC8q6gCwFEk0hP3axwFhrJOqOfUaQY2tKrPqet34cyFbXTqe_vG2oXSbaKtopJIH4TTyB7RHwP6py5HAPf8e5QyzA';
		$headers = array(
			'Authorization:key='.$s_key,
			'Content-Type:application/json'
		);
		
		$query = "SELECT token FROM tb_register_token";
		$run = mysqli_query($con, $query);
		$result = mysqli_fetch_array($run);
		 
		foreach($result as $key){
			$fields = array(
				'to'=>$key,
				'notification'=>array('title'=>$title,'body'=>$content)
			);
			$payload = json_encode($fields);
		
			$curl = curl_init();
			curl_setopt($curl, CURLOPT_URL, $path);
			curl_setopt($curl, CURLOPT_POST, true);
			curl_setopt($curl, CURLOPT_HTTPHEADER, $headers);
			curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
			curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
			curl_setopt($curl, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
			curl_setopt($curl, CURLOPT_POSTFIELDS, $payload);
			$final = curl_exec($curl);
			echo $final."<br>";
			curl_close($curl);
		}
	} */
	
	function validate($data){
		$data = trim($data);
		$data = stripslashes($data);
		$data = htmlspecialchars($data);
		return $data;
	}
?>