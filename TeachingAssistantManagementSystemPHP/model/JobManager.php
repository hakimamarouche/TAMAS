<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class JobManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobManager Attributes
  private $hourlyRate;

  //JobManager Associations
  private $courses;
  private $department;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aHourlyRate = null, $aDepartment = null)
  {
    if (func_num_args() == 0) { return; }

    $this->hourlyRate = $aHourlyRate;
    $this->courses = array();
    if ($aDepartment == null || $aDepartment->getTaManager() != null)
    {
      throw new Exception("Unable to create JobManager due to aDepartment");
    }
    $this->department = $aDepartment;
  }
  public static function newInstance($aHourlyRate)
  {
    $thisInstance = new JobManager();
    $thisInstance->hourlyRate = $aHourlyRate;
    $this->courses = array();
    $thisInstance->department = new Department($thisInstance);
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getHourlyRate()
  {
    return $this->hourlyRate;
  }

  public function getCourse_index($index)
  {
    $aCourse = $this->courses[$index];
    return $aCourse;
  }

  public function getCourses()
  {
    $newCourses = $this->courses;
    return $newCourses;
  }

  public function numberOfCourses()
  {
    $number = count($this->courses);
    return $number;
  }

  public function hasCourses()
  {
    $has = $this->numberOfCourses() > 0;
    return $has;
  }

  public function indexOfCourse($aCourse)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->courses as $course)
    {
      if ($course->equals($aCourse))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getDepartment()
  {
    return $this->department;
  }

  public static function minimumNumberOfCourses()
  {
    return 0;
  }

  public function addCourseVia($aTaWorkHours, $aGraderWorkHours, $aCoursCredit, $aCourseId, $aStudentsEnroled, $aBudget, $aInstructor)
  {
    return new Course($aTaWorkHours, $aGraderWorkHours, $aCoursCredit, $aCourseId, $aStudentsEnroled, $aBudget, $this, $aInstructor);
  }

  public function addCourse($aCourse)
  {
    $wasAdded = false;
    if ($this->indexOfCourse($aCourse) !== -1) { return false; }
    $existingJobManager = $aCourse->getJobManager();
    $isNewJobManager = $existingJobManager != null && $this !== $existingJobManager;
    if ($isNewJobManager)
    {
      $aCourse->setJobManager($this);
    }
    else
    {
      $this->courses[] = $aCourse;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeCourse($aCourse)
  {
    $wasRemoved = false;
    //Unable to remove aCourse, as it must always have a jobManager
    if ($this !== $aCourse->getJobManager())
    {
      unset($this->courses[$this->indexOfCourse($aCourse)]);
      $this->courses = array_values($this->courses);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addCourseAt($aCourse, $index)
  {  
    $wasAdded = false;
    if($this->addCourse($aCourse))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCourseAt($aCourse, $index)
  {
    $wasAdded = false;
    if($this->indexOfCourse($aCourse) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCourseAt($aCourse, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->courses) > 0)
    {
      $aCourse = $this->courses[count($this->courses) - 1];
      $aCourse->delete();
      unset($this->courses[$this->indexOfCourse($aCourse)]);
      $this->courses = array_values($this->courses);
    }
    
    $existingDepartment = $this->department;
    $this->department = null;
    if ($existingDepartment != null)
    {
      $existingDepartment->delete();
    }
  }

}
?>