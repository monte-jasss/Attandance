<?php
	require 'connection.php';
	
	$std_id = $_POST['std_id'];
	$sub_id = $_POST['sub_id'];
	$time = $_POST['time'];
	$date = $_POST['date'];
	$type = $_POST['type'];
	if($_POST['attendance']) $attendance = 1;
	else $attendance = 0;
	
	$result = array();
	if(isset($_REQUEST['attendance'])){
		$query = "INSERT INTO `attendance_table`(`student_id`, `subject_id`, `time`, `date`, `type`, `attendance`) VALUES ('$std_id','$sub_id','$time','$date','$type','$attendance') ON DUPLICATE KEY UPDATE `attendance`='$attendance'";
		if($run = mysqli_query($con, $query)){
			array_push($result,"success");
			echo json_encode($result);
		} else {
			array_push($result,"failed");
			echo json_encode($result);
		}
	} 
?>
