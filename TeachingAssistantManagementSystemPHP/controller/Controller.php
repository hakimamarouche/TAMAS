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
	
	public function makeJobOffers()
	{
		//The code here would take in a course and job description, find the course in the persistence department,
		//And then generate TaOffers and GraderOffer objects based on information on the course.
	}
	
	public function evaluateTA()
	{
		//The code here would take in the TA/Grader and evaluation string as arguments, and then access the
		//Persistence department, find the correct ta/grader and update the evaluation on that object.
	}

}