<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

class Department
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Department Associations
  private $taManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTaManager = null)
  {
    if (func_num_args() == 0) { return; }

    if ($aTaManager == null || $aTaManager->getDepartment() != null)
    {
      throw new Exception("Unable to create Department due to aTaManager");
    }
    $this->taManager = $aTaManager;
  }
  public static function newInstance($aHourlyRateForTaManager)
  {
    $thisInstance = new Department();
    $thisInstance->taManager = new JobManager($aHourlyRateForTaManager, $thisInstance);
    return $thisInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getTaManager()
  {
    return $this->taManager;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $existingTaManager = $this->taManager;
    $this->taManager = null;
    if ($existingTaManager != null)
    {
      $existingTaManager->delete();
    }
  }

}
?>