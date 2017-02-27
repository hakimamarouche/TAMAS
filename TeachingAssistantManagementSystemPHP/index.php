<<!DOCTYPE html>
<html>
	<head>
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
	
		//Retrieve the data from the model
		$pm = new PersistenceTams();
		$dpt = $pm->loadDataFromStore();
		$jm = $dpt->getTaManager();
		
// View Courses
		
		echo "<p>Course? <select name='coursespinner'>";
		foreach ($jm->getCourses() as $course) {
			echo "<option>" . $course->getCourseID() . "</option>";
		} 
		?>
		
		<form action="UpdateCourseInfo.php" method="post">
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
		
		<form action="ApplyForJob.php" method="post">
			<p>McGill ID? <input type="text" name="mcgill_id" />
			<p>Experience? <input type="text" name="experience" />
			<span class="error">
			<?php 
			if (isset($_SESSION['errorParticipantName']) && !empty($_SESSION['errorParticipantName'])) {
				echo " * " . $_SESSION['errorParticipantName'];
			}
			?>

			</span> </p>
			<p><input type="submit" value="Apply For Job"/></p>
		</form>
	</body>
</html>