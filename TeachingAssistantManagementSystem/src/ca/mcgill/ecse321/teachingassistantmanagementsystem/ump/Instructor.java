/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;
import java.util.*;

// line 29 "../../../../../TeachingAssistantManagementSystem.ump"
public class Instructor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Associations
  private List<Course> coursesTaught;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Instructor()
  {
    coursesTaught = new ArrayList<Course>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Course getCoursesTaught(int index)
  {
    Course aCoursesTaught = coursesTaught.get(index);
    return aCoursesTaught;
  }

  public List<Course> getCoursesTaught()
  {
    List<Course> newCoursesTaught = Collections.unmodifiableList(coursesTaught);
    return newCoursesTaught;
  }

  public int numberOfCoursesTaught()
  {
    int number = coursesTaught.size();
    return number;
  }

  public boolean hasCoursesTaught()
  {
    boolean has = coursesTaught.size() > 0;
    return has;
  }

  public int indexOfCoursesTaught(Course aCoursesTaught)
  {
    int index = coursesTaught.indexOf(aCoursesTaught);
    return index;
  }

  public static int minimumNumberOfCoursesTaught()
  {
    return 0;
  }

  public Course addCoursesTaught(int aTaWorkHours, int aGraderWorkHours, int aCoursCredit, String aCourseId, int aStudentsEnroled, int aBudget, JobManager aJobManager)
  {
    return new Course(aTaWorkHours, aGraderWorkHours, aCoursCredit, aCourseId, aStudentsEnroled, aBudget, aJobManager, this);
  }

  public boolean addCoursesTaught(Course aCoursesTaught)
  {
    boolean wasAdded = false;
    if (coursesTaught.contains(aCoursesTaught)) { return false; }
    Instructor existingInstructor = aCoursesTaught.getInstructor();
    boolean isNewInstructor = existingInstructor != null && !this.equals(existingInstructor);
    if (isNewInstructor)
    {
      aCoursesTaught.setInstructor(this);
    }
    else
    {
      coursesTaught.add(aCoursesTaught);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCoursesTaught(Course aCoursesTaught)
  {
    boolean wasRemoved = false;
    //Unable to remove aCoursesTaught, as it must always have a instructor
    if (!this.equals(aCoursesTaught.getInstructor()))
    {
      coursesTaught.remove(aCoursesTaught);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addCoursesTaughtAt(Course aCoursesTaught, int index)
  {  
    boolean wasAdded = false;
    if(addCoursesTaught(aCoursesTaught))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCoursesTaught()) { index = numberOfCoursesTaught() - 1; }
      coursesTaught.remove(aCoursesTaught);
      coursesTaught.add(index, aCoursesTaught);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCoursesTaughtAt(Course aCoursesTaught, int index)
  {
    boolean wasAdded = false;
    if(coursesTaught.contains(aCoursesTaught))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCoursesTaught()) { index = numberOfCoursesTaught() - 1; }
      coursesTaught.remove(aCoursesTaught);
      coursesTaught.add(index, aCoursesTaught);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCoursesTaughtAt(aCoursesTaught, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=coursesTaught.size(); i > 0; i--)
    {
      Course aCoursesTaught = coursesTaught.get(i - 1);
      aCoursesTaught.delete();
    }
  }

}