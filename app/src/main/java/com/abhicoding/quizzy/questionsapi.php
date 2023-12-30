<?php
// creating the connection
$conn = mysqli_connect("localhost","root","","quiz_db");

if (!$conn) {
    die('Error: ' . mysqli_connect_error());
}
$stmt = $conn->prepare("SELECT `question`,`option1`,`option2`,
		`option3`,`option4`,`correct_option` FROM `math_quiz`");

// executing the query
$stmt->execute();

// binding the results to the query
$stmt->bind_result($question, $option1, $option2, $option3, $option4, $correct_option);

// creating an empty  array
$questions_array = array();

// traversing through all the questions
while($stmt->fetch()){
	$temp = array();
	$temp['question'] = $question;
	$temp['option1'] = $option1;
	$temp['option2'] = $option2;
	$temp['option3'] = $option3;
	$temp['option4'] = $option4;
	$temp['correct_option'] = $correct_option;

	array_push($questions_array, $temp);
	
}

//  Displaying the results in JSON Format
echo json_encode($questions_array);

$stmt->close();
$conn->close();
?>