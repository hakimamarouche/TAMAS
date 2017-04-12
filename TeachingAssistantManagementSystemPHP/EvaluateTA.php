<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorEvaluation"] = "";
$c = new Controller();

try {
	//Did not have time to implement the controller method for this, but it would send the correct
	//TA/Grader object and the evaluation, and then store it.
	$c->evaluateTA();
} catch (Exception $e) {
	$_SESSION["errorEvaluation"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TeachingAssistantManagementSystemPHP/" />
	</head>
</html>