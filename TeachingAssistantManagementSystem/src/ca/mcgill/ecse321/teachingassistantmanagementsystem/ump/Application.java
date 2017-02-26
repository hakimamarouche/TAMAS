/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;

// line 35 "../../../../../TeachingAssistantManagementSystem.ump"
public class Application
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Attributes
  private String experience;

  //Application Associations
  private Applicant applicant;
  private JobOffer jobs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application(String aExperience, Applicant aApplicant, JobOffer aJobs)
  {
    experience = aExperience;
    boolean didAddApplicant = setApplicant(aApplicant);
    if (!didAddApplicant)
    {
      throw new RuntimeException("Unable to create application due to applicant");
    }
    boolean didAddJobs = setJobs(aJobs);
    if (!didAddJobs)
    {
      throw new RuntimeException("Unable to create application due to jobs");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setExperience(String aExperience)
  {
    boolean wasSet = false;
    experience = aExperience;
    wasSet = true;
    return wasSet;
  }

  public String getExperience()
  {
    return experience;
  }

  public Applicant getApplicant()
  {
    return applicant;
  }

  public JobOffer getJobs()
  {
    return jobs;
  }

  public boolean setApplicant(Applicant aApplicant)
  {
    boolean wasSet = false;
    if (aApplicant == null)
    {
      return wasSet;
    }

    Applicant existingApplicant = applicant;
    applicant = aApplicant;
    if (existingApplicant != null && !existingApplicant.equals(aApplicant))
    {
      existingApplicant.removeApplication(this);
    }
    applicant.addApplication(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setJobs(JobOffer aJobs)
  {
    boolean wasSet = false;
    if (aJobs == null)
    {
      return wasSet;
    }

    JobOffer existingJobs = jobs;
    jobs = aJobs;
    if (existingJobs != null && !existingJobs.equals(aJobs))
    {
      existingJobs.removeApplication(this);
    }
    jobs.addApplication(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Applicant placeholderApplicant = applicant;
    this.applicant = null;
    placeholderApplicant.removeApplication(this);
    JobOffer placeholderJobs = jobs;
    this.jobs = null;
    placeholderJobs.removeApplication(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "experience" + ":" + getExperience()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "applicant = "+(getApplicant()!=null?Integer.toHexString(System.identityHashCode(getApplicant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "jobs = "+(getJobs()!=null?Integer.toHexString(System.identityHashCode(getJobs())):"null")
     + outputString;
  }
}