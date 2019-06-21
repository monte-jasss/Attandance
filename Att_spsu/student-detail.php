<?php
	require 'connection.php';
	
	$id = $_REQUEST['id'];
	
	$result = array();
	if(isset($_REQUEST['id'])){
		$query = "SELECT student_id, enrollment, name, semester, year FROM student_table WHERE student_id='$id'";
		if($run = mysqli_query($con, $query)){
			while($item = mysqli_fetch_array($run)){
				array_push($result,array('student_id'=>$item['student_id'], 'enrollment'=>$item['enrollment'], 'name'=>$item['name'], 'semester'=>$item['semester'], 'year'=>$item['year']));
			}
			echo json_encode($result);
		}
	}
?>
