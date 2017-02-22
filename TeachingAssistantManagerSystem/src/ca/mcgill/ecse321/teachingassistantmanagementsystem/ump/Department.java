/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;

// line 31 "../../../../../TeachingAssistantManagementSystem.ump"
public class Department
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Department Associations
  private JobManager taManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Department(JobManager aTaManager)
  {
    if (aTaManager == null || aTaManager.getDepartment() != null)
    {
      throw new RuntimeException("Unable to create Department due to aTaManager");
    }
    taManager = aTaManager;
  }

  public Department(int aHourlyRateForTaManager)
  {
    taManager = new JobManager(aHourlyRateForTaManager, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public JobManager getTaManager()
  {
    return taManager;
  }

  public void delete()
  {
    JobManager existingTaManager = taManager;
    taManager = null;
    if (existingTaManager != null)
    {
      existingTaManager.delete();
    }
  }

}