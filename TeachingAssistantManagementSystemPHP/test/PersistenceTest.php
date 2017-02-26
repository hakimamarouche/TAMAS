<?php

require_once __DIR__.'\..\persistence\PersistenceTams.php';
require_once __DIR__.'\..\model\Department.php';
require_once __DIR__.'\..\model\Course.php';
require_once __DIR__.'\..\model\Instructor.php';
require_once __DIR__.'\..\model\JobManager.php';

class PersistenceTest extends PHPUnit_Framework_TestCase
{
	protected $pm;

	protected function setUp()
	{
		$this->pm = new PersistenceTams();
	}

	protected function tearDown()
	{
		
	}

	public function testPersistence()
	{
		// 1. Create test data
		$dptClass = new Department();
		$dpt = $dptClass->newInstance(4);
		$instructor = new Instructor();
		$jobManager = $dpt->getTaManager();
		$course = new Course(120, 80, 3, "ECSE321", 100, 5000, $jobManager, $instructor);
		$dpt->getTaManager()->addCourse($course);

		// 2. Write all of the data
		$this->pm->writeDataToStore($dpt);

		// 3. Clear the data from memory
		$dpt->delete();

		//$this->assertEquals(0, $dpt->getTaManager()->numberOfCourses() );

		// 4. Load it back in
		$dpt = $this->pm->loadDataFromStore();

		// 5. Check that we got it back
		$this->assertEquals(1, $dpt->getTaManager()->numberOfCourses() );

	}
}

?>