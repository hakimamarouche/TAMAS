/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;

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

  public void delete()
  {
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeJob(this);
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