<?php
	require 'connection.php';
	
	$id = $_REQUEST['id'];
	
	$result = array();
	if(isset($_REQUEST['id'])){
		$query = "SELECT B.subject_id, code, name, credit FROM subject_table as A INNER JOIN teach_sub_table as B ON A.subject_id = B.subject_id and B.user_id = '$id';";
		if($run = mysqli_query($con, $query)){
			while($item = mysqli_fetch_array($run)){
				array_push($result,array('subject_id'=>$item['subject_id'], 'code'=>$item['code'], 'name'=>$item['name'], 'credit'=>$item['credit']));
			}
			echo json_encode($result);
		}
	}
?>