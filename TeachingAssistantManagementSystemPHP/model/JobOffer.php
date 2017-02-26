<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class JobOffer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobOffer Attributes
  private $workHours;

  //JobOffer Associations
  private $course;
  private $applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aWorkHours, $aCourse)
  {
    $this->workHours = $aWorkHours;
    $didAddCourse = $this->setCourse($aCourse);
    if (!$didAddCourse)
    {
      throw new Exception("Unable to create job due to course");
    }
    $this->applications = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setWorkHours($aWorkHours)
  {
    $wasSet = false;
    $this->workHours = $aWorkHours;
    $wasSet = true;
    return $wasSet;
  }

  public function getWorkHours()
  {
    return $this->workHours;
  }

  public function getCourse()
  {
    return $this->course;
  }

  public function getApplication_index($index)
  {
    $aApplication = $this->applications[$index];
    return $aApplication;
  }

  public function getApplications()
  {
    $newApplications = $this->applications;
    return $newApplications;
  }

  public function numberOfApplications()
  {
    $number = count($this->applications);
    return $number;
  }

  public function hasApplications()
  {
    $has = $this->numberOfApplications() > 0;
    return $has;
  }

  public function indexOfApplication($aApplication)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->applications as $application)
    {
      if ($application->equals($aApplication))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function setCourse($aCourse)
  {
    $wasSet = false;
    if ($aCourse == null)
    {
      return $wasSet;
    }
    
    $existingCourse = $this->course;
    $this->course = $aCourse;
    if ($existingCourse != null && $existingCourse != $aCourse)
    {
      $existingCourse->removeJob($this);
    }
    $this->course->addJob($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfApplications()
  {
    return 0;
  }

  public function addApplicationVia($aExperience, $aApplicant)
  {
    return new Application($aExperience, $aApplicant, $this);
  }

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    $existingJobs = $aApplication->getJobs();
    $isNewJobs = $existingJobs != null && $this !== $existingJobs;
    if ($isNewJobs)
    {
      $aApplication->setJobs($this);
    }
    else
    {
      $this->applications[] = $aApplication;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeApplication($aApplication)
  {
    $wasRemoved = false;
    //Unable to remove aApplication, as it must always have a jobs
    if ($this !== $aApplication->getJobs())
    {
      unset($this->applications[$this->indexOfApplication($aApplication)]);
      $this->applications = array_values($this->applications);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addApplicationAt($aApplication, $index)
  {  
    $wasAdded = false;
    if($this->addApplication($aApplication))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplications()) { $index = $this->numberOfApplications() - 1; }
      array_splice($this->applications, $this->indexOfApplication($aApplication), 1);
      array_splice($this->applications, $index, 0, array($aApplication));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveApplicationAt($aApplication, $index)
  {
    $wasAdded = false;
    if($this->indexOfApplication($aApplication) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplications()) { $index = $this->numberOfApplications() - 1; }
      array_splice($this->applications, $this->indexOfApplication($aApplication), 1);
      array_splice($this->applications, $index, 0, array($aApplication));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addApplicationAt($aApplication, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderCourse = $this->course;
    $this->course = null;
    $placeholderCourse->removeJob($this);
    foreach ($this->applications as $aApplication)
    {
      $aApplication->delete();
    }
  }

}
?>