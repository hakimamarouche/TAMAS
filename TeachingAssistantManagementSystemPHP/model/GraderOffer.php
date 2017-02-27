<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class GraderOffer extends JobOffer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GraderOffer Attributes
  private $capacity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aWorkHours, $aCourse, $aCapacity)
  {
    parent::__construct($aWorkHours, $aCourse);
    $this->capacity = $aCapacity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setCapacity($aCapacity)
  {
    $wasSet = false;
    $this->capacity = $aCapacity;
    $wasSet = true;
    return $wasSet;
  }

  public function getCapacity()
  {
    return $this->capacity;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    parent::delete();
  }

}
?>