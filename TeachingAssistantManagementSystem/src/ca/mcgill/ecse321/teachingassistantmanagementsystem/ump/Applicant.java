/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;
import java.util.*;

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
  private List<Application> applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Applicant(int aMcgillId)
  {
    mcgillId = aMcgillId;
    applications = new ArrayList<Application>();
    setDegree(Degree.GRADUATE);
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

  public Application getApplication(int index)
  {
    Application aApplication = applications.get(index);
    return aApplication;
  }

  public List<Application> getApplications()
  {
    List<Application> newApplications = Collections.unmodifiableList(applications);
    return newApplications;
  }

  public int numberOfApplications()
  {
    int number = applications.size();
    return number;
  }

  public boolean hasApplications()
  {
    boolean has = applications.size() > 0;
    return has;
  }

  public int indexOfApplication(Application aApplication)
  {
    int index = applications.indexOf(aApplication);
    return index;
  }

  public static int minimumNumberOfApplications()
  {
    return 0;
  }

  public Application addApplication(String aExperience, JobOffer aJobs)
  {
    return new Application(aExperience, this, aJobs);
  }

  public boolean addApplication(Application aApplication)
  {
    boolean wasAdded = false;
    if (applications.contains(aApplication)) { return false; }
    Applicant existingApplicant = aApplication.getApplicant();
    boolean isNewApplicant = existingApplicant != null && !this.equals(existingApplicant);
    if (isNewApplicant)
    {
      aApplication.setApplicant(this);
    }
    else
    {
      applications.add(aApplication);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeApplication(Application aApplication)
  {
    boolean wasRemoved = false;
    //Unable to remove aApplication, as it must always have a applicant
    if (!this.equals(aApplication.getApplicant()))
    {
      applications.remove(aApplication);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addApplicationAt(Application aApplication, int index)
  {  
    boolean wasAdded = false;
    if(addApplication(aApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplications()) { index = numberOfApplications() - 1; }
      applications.remove(aApplication);
      applications.add(index, aApplication);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveApplicationAt(Application aApplication, int index)
  {
    boolean wasAdded = false;
    if(applications.contains(aApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplications()) { index = numberOfApplications() - 1; }
      applications.remove(aApplication);
      applications.add(index, aApplication);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addApplicationAt(aApplication, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=applications.size(); i > 0; i--)
    {
      Application aApplication = applications.get(i - 1);
      aApplication.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "mcgillId" + ":" + getMcgillId()+ "]"
     + outputString;
  }
}