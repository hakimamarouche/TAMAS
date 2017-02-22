/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;
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

  public Application(String aExperience, Applicant aApplicant, JobOffer... allJobs)
  {
    experience = aExperience;
    if (aApplicant == null || aApplicant.getApplication() != null)
    {
      throw new RuntimeException("Unable to create Application due to aApplicant");
    }
    applicant = aApplicant;
    jobs = new ArrayList<JobOffer>();
    boolean didAddJobs = setJobs(allJobs);
    if (!didAddJobs)
    {
      throw new RuntimeException("Unable to create Application, must have 1 to 3 jobs");
    }
  }

  public Application(String aExperience, int aMcgillIdForApplicant, JobOffer... allJobs)
  {
    experience = aExperience;
    applicant = new Applicant(aMcgillIdForApplicant, this);
    jobs = new ArrayList<JobOffer>();
    boolean didAddJobs = setJobs(allJobs);
    if (!didAddJobs)
    {
      throw new RuntimeException("Unable to create Application, must have 1 to 3 jobs");
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

  public boolean addJob(JobOffer aJob)
  {
    boolean wasAdded = false;
    if (jobs.contains(aJob)) { return false; }
    if (numberOfJobs() >= maximumNumberOfJobs())
    {
      return wasAdded;
    }

    jobs.add(aJob);
    if (aJob.indexOfApplication(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aJob.addApplication(this);
      if (!wasAdded)
      {
        jobs.remove(aJob);
      }
    }
    return wasAdded;
  }

  public boolean removeJob(JobOffer aJob)
  {
    boolean wasRemoved = false;
    if (!jobs.contains(aJob))
    {
      return wasRemoved;
    }

    if (numberOfJobs() <= minimumNumberOfJobs())
    {
      return wasRemoved;
    }

    int oldIndex = jobs.indexOf(aJob);
    jobs.remove(oldIndex);
    if (aJob.indexOfApplication(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aJob.removeApplication(this);
      if (!wasRemoved)
      {
        jobs.add(oldIndex,aJob);
      }
    }
    return wasRemoved;
  }

  public boolean setJobs(JobOffer... newJobs)
  {
    boolean wasSet = false;
    ArrayList<JobOffer> verifiedJobs = new ArrayList<JobOffer>();
    for (JobOffer aJob : newJobs)
    {
      if (verifiedJobs.contains(aJob))
      {
        continue;
      }
      verifiedJobs.add(aJob);
    }

    if (verifiedJobs.size() != newJobs.length || verifiedJobs.size() < minimumNumberOfJobs() || verifiedJobs.size() > maximumNumberOfJobs())
    {
      return wasSet;
    }

    ArrayList<JobOffer> oldJobs = new ArrayList<JobOffer>(jobs);
    jobs.clear();
    for (JobOffer aNewJob : verifiedJobs)
    {
      jobs.add(aNewJob);
      if (oldJobs.contains(aNewJob))
      {
        oldJobs.remove(aNewJob);
      }
      else
      {
        aNewJob.addApplication(this);
      }
    }

    for (JobOffer anOldJob : oldJobs)
    {
      anOldJob.removeApplication(this);
    }
    wasSet = true;
    return wasSet;
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
    ArrayList<JobOffer> copyOfJobs = new ArrayList<JobOffer>(jobs);
    jobs.clear();
    for(JobOffer aJob : copyOfJobs)
    {
      aJob.removeApplication(this);
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