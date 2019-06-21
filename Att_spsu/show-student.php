<?php
	require 'connection.php';
	
	$id = $_REQUEST['id'];
	
	$result = array();
	if(isset($_REQUEST['id'])){
		$query = "SELECT A.student_id, enrollment, name, semester, A.year FROM student_table as A INNER JOIN student_sub_table as B ON A.student_id = B.student_id and B.subject_id = '$id'";
		if($run = mysqli_query($con, $query)){
			while($item = mysqli_fetch_array($run)){
				array_push($result,array('student_id'=>$item['student_id'], 'enrollment'=>$item['enrollment'], 'name'=>$item['name'], 'semester'=>$item['semester'], 'year'=>$item['year']));
			}
			echo json_encode($result);
		}
	}
?>
