<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorJobPosting"] = "";
$c = new Controller();

try {
	//Didn't have time to finish all of this, but the function here would send the correct course object + text from the experience box 
	//to the controller class, which would then generate the ta and grader job offers
	$c->makeJobOffers();
} catch (Exception $e) {
	$_SESSION["errorJobPosting"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/TeachingAssistantManagementSystemPHP/" />
	</head>
</html>