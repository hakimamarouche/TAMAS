/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse223.teachingassistantmanagementsystem.ump;
import java.util.*;

// line 3 "../../../../../TeachingAssistantManagementSystem.ump"
public class JobManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //JobManager Attributes
  private int hourlyRate;

  //JobManager Associations
  private List<Course> courses;
  private Department department;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public JobManager(int aHourlyRate, Department aDepartment)
  {
    hourlyRate = aHourlyRate;
    courses = new ArrayList<Course>();
    if (aDepartment == null || aDepartment.getTaManager() != null)
    {
      throw new RuntimeException("Unable to create JobManager due to aDepartment");
    }
    department = aDepartment;
  }

  public JobManager(int aHourlyRate)
  {
    hourlyRate = aHourlyRate;
    courses = new ArrayList<Course>();
    department = new Department(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getHourlyRate()
  {
    return hourlyRate;
  }

  public Course getCourse(int index)
  {
    Course aCourse = courses.get(index);
    return aCourse;
  }

  public List<Course> getCourses()
  {
    List<Course> newCourses = Collections.unmodifiableList(courses);
    return newCourses;
  }

  public int numberOfCourses()
  {
    int number = courses.size();
    return number;
  }

  public boolean hasCourses()
  {
    boolean has = courses.size() > 0;
    return has;
  }

  public int indexOfCourse(Course aCourse)
  {
    int index = courses.indexOf(aCourse);
    return index;
  }

  public Department getDepartment()
  {
    return department;
  }

  public static int minimumNumberOfCourses()
  {
    return 0;
  }

  public Course addCourse(int aTaWorkHours, int aGraderWorkHours, int aCoursCredit, String aCourseId, int aStudentsEnroled, int aBudget, Instructor aInstructor)
  {
    return new Course(aTaWorkHours, aGraderWorkHours, aCoursCredit, aCourseId, aStudentsEnroled, aBudget, this, aInstructor);
  }

  public boolean addCourse(Course aCourse)
  {
    boolean wasAdded = false;
    if (courses.contains(aCourse)) { return false; }
    JobManager existingJobManager = aCourse.getJobManager();
    boolean isNewJobManager = existingJobManager != null && !this.equals(existingJobManager);
    if (isNewJobManager)
    {
      aCourse.setJobManager(this);
    }
    else
    {
      courses.add(aCourse);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCourse(Course aCourse)
  {
    boolean wasRemoved = false;
    //Unable to remove aCourse, as it must always have a jobManager
    if (!this.equals(aCourse.getJobManager()))
    {
      courses.remove(aCourse);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCourseAt(Course aCourse, int index)
  {  
    boolean wasAdded = false;
    if(addCourse(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseAt(Course aCourse, int index)
  {
    boolean wasAdded = false;
    if(courses.contains(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseAt(aCourse, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (courses.size() > 0)
    {
      Course aCourse = courses.get(courses.size() - 1);
      aCourse.delete();
      courses.remove(aCourse);
    }
    
    Department existingDepartment = department;
    department = null;
    if (existingDepartment != null)
    {
      existingDepartment.delete();
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "hourlyRate" + ":" + getHourlyRate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "department = "+(getDepartment()!=null?Integer.toHexString(System.identityHashCode(getDepartment())):"null")
     + outputString;
  }
}