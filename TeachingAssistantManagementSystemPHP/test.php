<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorApply"] = "";
$c = new Controller();
$c->testMakeCourse();
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TeachingAssistantManagementSystemPHP/" />
	</head>
</html>