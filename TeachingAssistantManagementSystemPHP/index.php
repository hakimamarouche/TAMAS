<!DOCTYPE html>

<html>
	<head>
		<link href="style.css" type='text/css' rel="stylesheet">
		<meta charset="UTF-8">
		<title>Teaching Assistant Management System</title>
		<style>
			.error {color: #FF0000;}
		</style>
	</head>
	<body>
		<?php 
		require_once __DIR__.'\persistence\PersistenceTams.php';
		require_once __DIR__.'\model\Department.php';
		require_once __DIR__.'\model\Course.php';
		require_once __DIR__.'\model\Instructor.php';
		require_once __DIR__.'\model\JobManager.php';
		require_once __DIR__.'\model\JobOffer.php';
		require_once __DIR__.'\model\Applicant.php';
		require_once __DIR__.'\model\Application.php';
		
		session_start();

		$pm = new PersistenceTams();
		$dptClass = new Department();
		$dpt = $dptClass->newInstance(4);
		$jobManager = $dpt->getTaManager();
		$instructor = new Instructor();
		$course = new Course(120, 80, 3, "ECSE321", 100, 5000, $jobManager, $instructor);
		$dpt->getTaManager()->addCourse($course);
	
		$pm->writeDataToStore($dpt);
		
		$jobManager = $dpt->getTaManager();

		
		?>
		<h1>Instructor</h1>
		<form action="UpdateCourseInfo.php" method="post">
		
			<h3>View Courses</h3>
			<?php 
			if (isset($_SESSION['errorCourseInfo']) && !empty($_SESSION['errorCourseInfo']))
			{
				echo " * " . $_SESSION["errorCourseInfo"];
			}
			?>


			<?php 
			echo "<p>Course? <select name='coursespinner'>";
			foreach ($jobManager->getCourses() as $key) {
				echo "<option>" . $key->courseId . "</option>";
			}
			echo "</select>";
			?>

			<p>Course ID: 
			<?php 
			if (isset($_SESSION['courseID']) && !empty($_SESSION['courseID'])) {
				echo " " . $_SESSION['courseID'];
			}
			?>
			</p>
			
			<p>Credits:
			<?php 
			if (isset($_SESSION['courseCredits']) && !empty($_SESSION['courseCredits'])) {
				echo " * " . $_SESSION['courseCredits'];
			}
			?>
			</p>
			
			<p>TA Jobs: 
			<?php 
			if (isset($_SESSION['courseTAHours']) && !empty($_SESSION['courseTAHours'])) {
				echo " * " . $_SESSION['courseTAHours'];
			}
			?>
			</p>
			
			<p>Grader Hours:
			<?php 
			if (isset($_SESSION['courseGraderHours']) && !empty($_SESSION['courseGraderHours'])) {
				echo " * " . $_SESSION['courseGraderHours'];
			}
			?>
			</p>
			
			<p><input type="submit" value="Get Course Info"/></p>
		</form>
		
		<form action="PublishPosting.php" method="post">
			<?php 
			if (isset($_SESSION['errorJobPosting']) && !empty($_SESSION['errorJobPosting']))
			{
				echo " * " . $_SESSION["errorJobPosting"];
			}
			?>
			
			<h3>Publish Job Postings</h3>
			<?php 
			echo "<p>Course <select name='coursespinner'>";
			foreach ($jobManager->getCourses() as $key) {
				echo "<option>" . $key->courseId . "</option>";
			}
			echo "</select>";
			?>

			</select></p>

			<p>Required Experience <input type="text" name="experience" />
			<span class="error">
			<?php 
			if (isset($_SESSION['errorExperience']) && !empty($_SESSION['errorExperience'])) {
				echo " * " . $_SESSION['errorExperience'];
			}
			?>

			</span> </p>
			<p><input type="submit" value="Publish Job"/></p>
		
		</form>
		
		<form action="EvaluateTA.php" method="post">
			<?php 
			if (isset($_SESSION['errorEvaluation']) && !empty($_SESSION['errorEvaluation']))
			{
				echo " * " . $_SESSION["errorEvaluation"];
			}
			?>
			
			<h3>Evaluate TAs</h3>
			<?php 
			
			$hardcoded = array(
					"Andrew Tran",
					"Antoine Khouri"
			);
			
			echo "<p>TA <select name='taspinner'>";
			foreach ($hardcoded as $key) {
				echo "<option>" . $key . "</option>";
			}
			echo "</select>";
			?>

			</select></p>

			<p>Feedback <input type="text" name="feedback" />
			<span class="error">
			<?php 
			if (isset($_SESSION['errorFeedback']) && !empty($_SESSION['errorFeedback'])) {
				echo " * " . $_SESSION['errorFeedback'];
			}
			?>

			</span> </p>
			<p><input type="submit" value="Submit Evaluation"/></p>
		
		</form>
		
		
	</body>
</html>