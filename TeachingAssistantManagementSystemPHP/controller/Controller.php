<?php

require_once __DIR__.'\..\persistence\PersistenceTams.php';
require_once __DIR__.'\..\model\Department.php';
require_once __DIR__.'\..\model\Course.php';
require_once __DIR__.'\..\model\Instructor.php';
require_once __DIR__.'\..\model\JobManager.php';
require_once __DIR__.'\..\model\JobOffer.php';
require_once __DIR__.'\..\model\Applicant.php';
require_once __DIR__.'\..\model\Application.php';


class Controller
{
	private $pm;
	private $dpt;
	private $jm;
	
	public function __construct()
	{
		$pm = new PersistenceTams();
		$dpt = $pm->loadDataFromStore();
		$jm = $dpt->getTaManager();
	}
	
	public function viewCourses()
	{
		return $this->$jm;
	}
	
	public function applyForJob($mcgillID, $experience, $job)
	{
		// error handling
		$error = "";
		$errorHappened = 0;
		
		if($mcgillID<10000000){
			$error = error + "Invalid mcgill ID. ";
		}
		if (strlen($experience) < 30)
		{
			$error = error + "Experience must be atleast 30 characters long. ";
		}
		if($job == null){
			$error = error + "Select a job to apply to.";
		}
		if($errorHappened == 1)
		{
			throw new Exception($error);
		}
		
		// Adding applications to job offer
		$applicant = new Applicant($mcgillID);
		$application = new Application($experience, $applicant, $job);
		$job->addApplication($application);
	}
	
// 	public function testMakeCourse()
// 	{
// 		$instructor = new Instructor();
// 		$jm->addCourseVia(120, 180, 3, ECSE321, 100, 5000, $instructor);
// 	}
}