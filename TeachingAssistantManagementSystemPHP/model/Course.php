<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private $taWorkHours;
  private $graderWorkHours;
  private $coursCredit;
  private $courseId;
  private $studentsEnroled;
  private $budget;

  //Course Associations
  private $job;
  private $jobManager;
  private $instructor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTaWorkHours, $aGraderWorkHours, $aCoursCredit, $aCourseId, $aStudentsEnroled, $aBudget, $aJobManager, $aInstructor)
  {
    $this->taWorkHours = $aTaWorkHours;
    $this->graderWorkHours = $aGraderWorkHours;
    $this->coursCredit = $aCoursCredit;
    $this->courseId = $aCourseId;
    $this->studentsEnroled = $aStudentsEnroled;
    $this->budget = $aBudget;
    $this->job = array();
    $didAddJobManager = $this->setJobManager($aJobManager);
    if (!$didAddJobManager)
    {
      throw new Exception("Unable to create course due to jobManager");
    }
    $didAddInstructor = $this->setInstructor($aInstructor);
    if (!$didAddInstructor)
    {
      throw new Exception("Unable to create coursesTaught due to instructor");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setTaWorkHours($aTaWorkHours)
  {
    $wasSet = false;
    $this->taWorkHours = $aTaWorkHours;
    $wasSet = true;
    return $wasSet;
  }

  public function setGraderWorkHours($aGraderWorkHours)
  {
    $wasSet = false;
    $this->graderWorkHours = $aGraderWorkHours;
    $wasSet = true;
    return $wasSet;
  }

  public function setCoursCredit($aCoursCredit)
  {
    $wasSet = false;
    $this->coursCredit = $aCoursCredit;
    $wasSet = true;
    return $wasSet;
  }

  public function setCourseId($aCourseId)
  {
    $wasSet = false;
    $this->courseId = $aCourseId;
    $wasSet = true;
    return $wasSet;
  }

  public function setStudentsEnroled($aStudentsEnroled)
  {
    $wasSet = false;
    $this->studentsEnroled = $aStudentsEnroled;
    $wasSet = true;
    return $wasSet;
  }

  public function setBudget($aBudget)
  {
    $wasSet = false;
    $this->budget = $aBudget;
    $wasSet = true;
    return $wasSet;
  }

  public function getTaWorkHours()
  {
    return $this->taWorkHours;
  }

  public function getGraderWorkHours()
  {
    return $this->graderWorkHours;
  }

  public function getCoursCredit()
  {
    return $this->coursCredit;
  }

  public function getCourseId()
  {
    return $this->courseId;
  }

  public function getStudentsEnroled()
  {
    return $this->studentsEnroled;
  }

  public function getBudget()
  {
    return $this->budget;
  }

  public function getJob_index($index)
  {
    $aJob = $this->job[$index];
    return $aJob;
  }

  public function getJob()
  {
    $newJob = $this->job;
    return $newJob;
  }

  public function numberOfJob()
  {
    $number = count($this->job);
    return $number;
  }

  public function hasJob()
  {
    $has = $this->numberOfJob() > 0;
    return $has;
  }

  public function indexOfJob($aJob)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->job as $job)
    {
      if ($job->equals($aJob))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getJobManager()
  {
    return $this->jobManager;
  }

  public function getInstructor()
  {
    return $this->instructor;
  }

  public static function minimumNumberOfJob()
  {
    return 0;
  }

  public function addJobVia($aWorkHours)
  {
    return new JobOffer($aWorkHours, $this);
  }

  public function addJob($aJob)
  {
    $wasAdded = false;
    if ($this->indexOfJob($aJob) !== -1) { return false; }
    $existingCourse = $aJob->getCourse();
    $isNewCourse = $existingCourse != null && $this !== $existingCourse;
    if ($isNewCourse)
    {
      $aJob->setCourse($this);
    }
    else
    {
      $this->job[] = $aJob;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeJob($aJob)
  {
    $wasRemoved = false;
    //Unable to remove aJob, as it must always have a course
    if ($this !== $aJob->getCourse())
    {
      unset($this->job[$this->indexOfJob($aJob)]);
      $this->job = array_values($this->job);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addJobAt($aJob, $index)
  {  
    $wasAdded = false;
    if($this->addJob($aJob))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJob()) { $index = $this->numberOfJob() - 1; }
      array_splice($this->job, $this->indexOfJob($aJob), 1);
      array_splice($this->job, $index, 0, array($aJob));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveJobAt($aJob, $index)
  {
    $wasAdded = false;
    if($this->indexOfJob($aJob) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJob()) { $index = $this->numberOfJob() - 1; }
      array_splice($this->job, $this->indexOfJob($aJob), 1);
      array_splice($this->job, $index, 0, array($aJob));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addJobAt($aJob, $index);
    }
    return $wasAdded;
  }

  public function setJobManager($aJobManager)
  {
    $wasSet = false;
    if ($aJobManager == null)
    {
      return $wasSet;
    }
    
    $existingJobManager = $this->jobManager;
    $this->jobManager = $aJobManager;
    if ($existingJobManager != null && $existingJobManager != $aJobManager)
    {
      $existingJobManager->removeCourse($this);
    }
    $this->jobManager->addCourse($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setInstructor($aInstructor)
  {
    $wasSet = false;
    if ($aInstructor == null)
    {
      return $wasSet;
    }
    
    $existingInstructor = $this->instructor;
    $this->instructor = $aInstructor;
    if ($existingInstructor != null && $existingInstructor != $aInstructor)
    {
      $existingInstructor->removeCoursesTaught($this);
    }
    $this->instructor->addCoursesTaught($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->job) > 0)
    {
      $aJob = $this->job[count($this->job) - 1];
      $aJob->delete();
      unset($this->job[$this->indexOfJob($aJob)]);
      $this->job = array_values($this->job);
    }
    
    $placeholderJobManager = $this->jobManager;
    $this->jobManager = null;
    $placeholderJobManager->removeCourse($this);
    $placeholderInstructor = $this->instructor;
    $this->instructor = null;
    $placeholderInstructor->removeCoursesTaught($this);
  }

}
?>