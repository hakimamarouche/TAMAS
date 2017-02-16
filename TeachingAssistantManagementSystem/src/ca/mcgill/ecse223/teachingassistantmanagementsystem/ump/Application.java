/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.teachingassistantmanagementsystem.ump;
import java.util.*;

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
  private List<JobOffer> jobs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application(String aExperience, Applicant aApplicant)
  {
    experience = aExperience;
    if (aApplicant == null || aApplicant.getApplication() != null)
    {
      throw new RuntimeException("Unable to create Application due to aApplicant");
    }
    applicant = aApplicant;
    jobs = new ArrayList<JobOffer>();
  }

  public Application(String aExperience, int aMcgillIdForApplicant)
  {
    experience = aExperience;
    applicant = new Applicant(aMcgillIdForApplicant, this);
    jobs = new ArrayList<JobOffer>();
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

  public JobOffer getJob(int index)
  {
    JobOffer aJob = jobs.get(index);
    return aJob;
  }

  public List<JobOffer> getJobs()
  {
    List<JobOffer> newJobs = Collections.unmodifiableList(jobs);
    return newJobs;
  }

  public int numberOfJobs()
  {
    int number = jobs.size();
    return number;
  }

  public boolean hasJobs()
  {
    boolean has = jobs.size() > 0;
    return has;
  }

  public int indexOfJob(JobOffer aJob)
  {
    int index = jobs.indexOf(aJob);
    return index;
  }

  public boolean isNumberOfJobsValid()
  {
    boolean isValid = numberOfJobs() >= minimumNumberOfJobs() && numberOfJobs() <= maximumNumberOfJobs();
    return isValid;
  }

  public static int minimumNumberOfJobs()
  {
    return 1;
  }

  public static int maximumNumberOfJobs()
  {
    return 3;
  }

  public JobOffer addJob(int aWorkHours, Course aCourse)
  {
    if (numberOfJobs() >= maximumNumberOfJobs())
    {
      return null;
    }
    else
    {
      return new JobOffer(aWorkHours, aCourse, this);
    }
  }

  public boolean addJob(JobOffer aJob)
  {
    boolean wasAdded = false;
    if (jobs.contains(aJob)) { return false; }
    if (numberOfJobs() >= maximumNumberOfJobs())
    {
      return wasAdded;
    }

    Application existingApplication = aJob.getApplication();
    boolean isNewApplication = existingApplication != null && !this.equals(existingApplication);

    if (isNewApplication && existingApplication.numberOfJobs() <= minimumNumberOfJobs())
    {
      return wasAdded;
    }

    if (isNewApplication)
    {
      aJob.setApplication(this);
    }
    else
    {
      jobs.add(aJob);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJob(JobOffer aJob)
  {
    boolean wasRemoved = false;
    //Unable to remove aJob, as it must always have a application
    if (this.equals(aJob.getApplication()))
    {
      return wasRemoved;
    }

    //application already at minimum (1)
    if (numberOfJobs() <= minimumNumberOfJobs())
    {
      return wasRemoved;
    }
    jobs.remove(aJob);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addJobAt(JobOffer aJob, int index)
  {  
    boolean wasAdded = false;
    if(addJob(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobAt(JobOffer aJob, int index)
  {
    boolean wasAdded = false;
    if(jobs.contains(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobAt(aJob, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Applicant existingApplicant = applicant;
    applicant = null;
    if (existingApplicant != null)
    {
      existingApplicant.delete();
    }
    for(int i=jobs.size(); i > 0; i--)
    {
      JobOffer aJob = jobs.get(i - 1);
      aJob.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "experience" + ":" + getExperience()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "applicant = "+(getApplicant()!=null?Integer.toHexString(System.identityHashCode(getApplicant())):"null")
     + outputString;
  }
}