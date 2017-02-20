/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;

// line 17 "../../../../../TeachingAssistantManagementSystem.ump"
public class Applicant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Applicant Attributes
  private int mcgillId;

  //Applicant State Machines
  public enum Degree { GRADUATE, UNDERGRADUATE }
  private Degree degree;

  //Applicant Associations
  private Application application;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Applicant(int aMcgillId, Application aApplication)
  {
    mcgillId = aMcgillId;
    if (aApplication == null || aApplication.getApplicant() != null)
    {
      throw new RuntimeException("Unable to create Applicant due to aApplication");
    }
    application = aApplication;
    setDegree(Degree.GRADUATE);
  }

  public Applicant(int aMcgillId, String aExperienceForApplication, JobOffer... allJobsForApplication)
  {
    mcgillId = aMcgillId;
    application = new Application(aExperienceForApplication, this, allJobsForApplication);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMcgillId(int aMcgillId)
  {
    boolean wasSet = false;
    mcgillId = aMcgillId;
    wasSet = true;
    return wasSet;
  }

  public int getMcgillId()
  {
    return mcgillId;
  }

  public String getDegreeFullName()
  {
    String answer = degree.toString();
    return answer;
  }

  public Degree getDegree()
  {
    return degree;
  }

  public boolean setDegree(Degree aDegree)
  {
    degree = aDegree;
    return true;
  }

  public Application getApplication()
  {
    return application;
  }

  public void delete()
  {
    Application existingApplication = application;
    application = null;
    if (existingApplication != null)
    {
      existingApplication.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "mcgillId" + ":" + getMcgillId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "application = "+(getApplication()!=null?Integer.toHexString(System.identityHashCode(getApplication())):"null")
     + outputString;
  }
}