<?php

require_once 'controller/Controller.php';

session_start();

$_SESSION["errorCourseInfo"] = "";
$c = new Controller();
$course = NULL;
if (isset($_POST['coursespinner'])) {
	//Temp code -> need to get course object from controller
	$course = $_POST['coursespinner'];
	$_SESSION["courseID"] = "ECSE321";
	$_SESSION["courseCredits"] = "3";
	$_SESSION["courseTAHours"] = "120";
	$_SESSION["courseGraderHours"] = "80";
}
else	
{
	$_SESSION["errorCourseInfo"] = "No course selected!";
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TeachingAssistantManagementSystemPHP/" />
	</head>
</html>