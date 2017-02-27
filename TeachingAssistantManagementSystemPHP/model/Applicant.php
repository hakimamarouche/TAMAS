<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class Applicant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Applicant Attributes
  private $mcgillId;

  //Applicant State Machines
  private static $DegreeGRADUATE = 1;
  private static $DegreeUNDERGRADUATE = 2;
  private $degree;

  //Applicant Associations
  private $applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aMcgillId)
  {
    $this->mcgillId = $aMcgillId;
    $this->applications = array();
    $this->setDegree(self::$DegreeGRADUATE);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setMcgillId($aMcgillId)
  {
    $wasSet = false;
    $this->mcgillId = $aMcgillId;
    $wasSet = true;
    return $wasSet;
  }

  public function getMcgillId()
  {
    return $this->mcgillId;
  }

  public function getDegreeFullName()
  {
    $answer = $this->getDegree();
    return $answer;
  }

  public function getDegree()
  {
    if ($this->degree == self::$DegreeGRADUATE) { return "DegreeGRADUATE"; }
    elseif ($this->degree == self::$DegreeUNDERGRADUATE) { return "DegreeUNDERGRADUATE"; }
    return null;
  }

  public function setDegree($aDegree)
  {
    if ($aDegree == "DegreeGRADUATE" || $aDegree == self::$DegreeGRADUATE)
    {
      $this->degree = self::$DegreeGRADUATE;
      return true;
    }
    elseif ($aDegree == "DegreeUNDERGRADUATE" || $aDegree == self::$DegreeUNDERGRADUATE)
    {
      $this->degree = self::$DegreeUNDERGRADUATE;
      return true;
    }
    else
    {
      return false;
    }
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

  public static function minimumNumberOfApplications()
  {
    return 0;
  }

  public function addApplicationVia($aExperience, $aJobs)
  {
    return new Application($aExperience, $this, $aJobs);
  }

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    $existingApplicant = $aApplication->getApplicant();
    $isNewApplicant = $existingApplicant != null && $this !== $existingApplicant;
    if ($isNewApplicant)
    {
      $aApplication->setApplicant($this);
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
    //Unable to remove aApplication, as it must always have a applicant
    if ($this !== $aApplication->getApplicant())
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
    foreach ($this->applications as $aApplication)
    {
      $aApplication->delete();
    }
  }

}
?>