<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorApply"] = "";
$c = new Controller();

try {
	$course = NULL;
	if (isset($_POST['coursespinner'])){
		$course = $_POST['coursespinner'];
		$job = $course->getJob();
	}
	$c->applyForJob($_POST['mcgill_ID'], $_POST['mcgill_ID'], $job);
	
} catch (Exception $e) {
	$_SESSION["errorApply"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TeachingAssistantManagementSystemPHP/" />
	</head>
</html>