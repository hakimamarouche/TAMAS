<?php

require_once 'controller/Controller.php';

session_start();

$_SESSION["errorApply"] = "";
$c = new Controller();
$course = NULL;
if (isset($_POST['coursespinner'])) {
	$course = $_POST['coursespinner'];
	$_SESSION["courseID"] = $course->getCourseId();
	$_SESSION["courseCredits"] = $course->getCoursCredit();
	$_SESSION["courseTAHours"] = $course->getTaWorkHours();
	$_SESSION["courseGraderHours"] = $course->getGraderWorkHours();
}
if ($course = NULL);	
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