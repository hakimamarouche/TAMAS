<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class Instructor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Associations
  private $coursesTaught;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->coursesTaught = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getCoursesTaught_index($index)
  {
    $aCoursesTaught = $this->coursesTaught[$index];
    return $aCoursesTaught;
  }

  public function getCoursesTaught()
  {
    $newCoursesTaught = $this->coursesTaught;
    return $newCoursesTaught;
  }

  public function numberOfCoursesTaught()
  {
    $number = count($this->coursesTaught);
    return $number;
  }

  public function hasCoursesTaught()
  {
    $has = $this->numberOfCoursesTaught() > 0;
    return $has;
  }

  public function indexOfCoursesTaught($aCoursesTaught)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->coursesTaught as $coursesTaught)
    {
      if ($coursesTaught->equals($aCoursesTaught))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfCoursesTaught()
  {
    return 0;
  }

  public function addCoursesTaughtVia($aTaWorkHours, $aGraderWorkHours, $aCoursCredit, $aCourseId, $aStudentsEnroled, $aBudget, $aJobManager)
  {
    return new Course($aTaWorkHours, $aGraderWorkHours, $aCoursCredit, $aCourseId, $aStudentsEnroled, $aBudget, $aJobManager, $this);
  }

  public function addCoursesTaught($aCoursesTaught)
  {
    $wasAdded = false;
    if ($this->indexOfCoursesTaught($aCoursesTaught) !== -1) { return false; }
    $existingInstructor = $aCoursesTaught->getInstructor();
    $isNewInstructor = $existingInstructor != null && $this !== $existingInstructor;
    if ($isNewInstructor)
    {
      $aCoursesTaught->setInstructor($this);
    }
    else
    {
      $this->coursesTaught[] = $aCoursesTaught;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeCoursesTaught($aCoursesTaught)
  {
    $wasRemoved = false;
    //Unable to remove aCoursesTaught, as it must always have a instructor
    if ($this !== $aCoursesTaught->getInstructor())
    {
      unset($this->coursesTaught[$this->indexOfCoursesTaught($aCoursesTaught)]);
      $this->coursesTaught = array_values($this->coursesTaught);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addCoursesTaughtAt($aCoursesTaught, $index)
  {  
    $wasAdded = false;
    if($this->addCoursesTaught($aCoursesTaught))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCoursesTaught()) { $index = $this->numberOfCoursesTaught() - 1; }
      array_splice($this->coursesTaught, $this->indexOfCoursesTaught($aCoursesTaught), 1);
      array_splice($this->coursesTaught, $index, 0, array($aCoursesTaught));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCoursesTaughtAt($aCoursesTaught, $index)
  {
    $wasAdded = false;
    if($this->indexOfCoursesTaught($aCoursesTaught) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCoursesTaught()) { $index = $this->numberOfCoursesTaught() - 1; }
      array_splice($this->coursesTaught, $this->indexOfCoursesTaught($aCoursesTaught), 1);
      array_splice($this->coursesTaught, $index, 0, array($aCoursesTaught));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCoursesTaughtAt($aCoursesTaught, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->coursesTaught as $aCoursesTaught)
    {
      $aCoursesTaught->delete();
    }
  }

}
?>