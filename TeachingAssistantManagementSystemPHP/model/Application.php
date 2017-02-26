<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class Application
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Attributes
  private $experience;

  //Application Associations
  private $applicant;
  private $jobs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aExperience, $aApplicant, $aJobs)
  {
    $this->experience = $aExperience;
    $didAddApplicant = $this->setApplicant($aApplicant);
    if (!$didAddApplicant)
    {
      throw new Exception("Unable to create application due to applicant");
    }
    $didAddJobs = $this->setJobs($aJobs);
    if (!$didAddJobs)
    {
      throw new Exception("Unable to create application due to jobs");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setExperience($aExperience)
  {
    $wasSet = false;
    $this->experience = $aExperience;
    $wasSet = true;
    return $wasSet;
  }

  public function getExperience()
  {
    return $this->experience;
  }

  public function getApplicant()
  {
    return $this->applicant;
  }

  public function getJobs()
  {
    return $this->jobs;
  }

  public function setApplicant($aApplicant)
  {
    $wasSet = false;
    if ($aApplicant == null)
    {
      return $wasSet;
    }
    
    $existingApplicant = $this->applicant;
    $this->applicant = $aApplicant;
    if ($existingApplicant != null && $existingApplicant != $aApplicant)
    {
      $existingApplicant->removeApplication($this);
    }
    $this->applicant->addApplication($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setJobs($aJobs)
  {
    $wasSet = false;
    if ($aJobs == null)
    {
      return $wasSet;
    }
    
    $existingJobs = $this->jobs;
    $this->jobs = $aJobs;
    if ($existingJobs != null && $existingJobs != $aJobs)
    {
      $existingJobs->removeApplication($this);
    }
    $this->jobs->addApplication($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderApplicant = $this->applicant;
    $this->applicant = null;
    $placeholderApplicant->removeApplication($this);
    $placeholderJobs = $this->jobs;
    $this->jobs = null;
    $placeholderJobs->removeApplication($this);
  }

}
?>