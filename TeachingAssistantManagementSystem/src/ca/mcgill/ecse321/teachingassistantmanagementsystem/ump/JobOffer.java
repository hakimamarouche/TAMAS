/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;
import java.util.*;

// line 23 "../../../../../TeachingAssistantManagementSystem.ump"
public class JobOffer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobOffer Attributes
  private int workHours;

  //JobOffer Associations
  private Course course;
  private List<Application> applications;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public JobOffer(int aWorkHours, Course aCourse)
  {
    workHours = aWorkHours;
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create job due to course");
    }
    applications = new ArrayList<Application>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWorkHours(int aWorkHours)
  {
    boolean wasSet = false;
    workHours = aWorkHours;
    wasSet = true;
    return wasSet;
  }

  public int getWorkHours()
  {
    return workHours;
  }

  public Course getCourse()
  {
    return course;
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

  public boolean setCourse(Course aCourse)
  {
    boolean wasSet = false;
    if (aCourse == null)
    {
      return wasSet;
    }

    Course existingCourse = course;
    course = aCourse;
    if (existingCourse != null && !existingCourse.equals(aCourse))
    {
      existingCourse.removeJob(this);
    }
    course.addJob(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfApplications()
  {
    return 0;
  }

  public boolean addApplication(Application aApplication)
  {
    boolean wasAdded = false;
    if (applications.contains(aApplication)) { return false; }
    if (applications.contains(aApplication)) { return false; }
    if (applications.contains(aApplication)) { return false; }
    applications.add(aApplication);
    if (aApplication.indexOfJob(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aApplication.addJob(this);
      if (!wasAdded)
      {
        applications.remove(aApplication);
      }
    }
    return wasAdded;
  }

  public boolean removeApplication(Application aApplication)
  {
    boolean wasRemoved = false;
    if (!applications.contains(aApplication))
    {
      return wasRemoved;
    }

    int oldIndex = applications.indexOf(aApplication);
    applications.remove(oldIndex);
    if (aApplication.indexOfJob(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aApplication.removeJob(this);
      if (!wasRemoved)
      {
        applications.add(oldIndex,aApplication);
      }
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
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeJob(this);
    ArrayList<Application> copyOfApplications = new ArrayList<Application>(applications);
    applications.clear();
    for(Application aApplication : copyOfApplications)
    {
      if (aApplication.numberOfJobs() <= Application.minimumNumberOfJobs())
      {
        aApplication.delete();
      }
      else
      {
        aApplication.removeJob(this);
      }
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "workHours" + ":" + getWorkHours()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null")
     + outputString;
  }
}