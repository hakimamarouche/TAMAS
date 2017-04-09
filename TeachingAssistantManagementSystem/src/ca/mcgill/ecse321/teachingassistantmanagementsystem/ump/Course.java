/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;
import java.util.*;

// line 7 "../../../../../TeachingAssistantManagementSystem.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private int taWorkHours;
  private int graderWorkHours;
  private int coursCredit;
  private String courseId;
  private int studentsEnroled;
  private int budget;

  //Course Associations
  private List<JobOffer> job;
  private JobManager jobManager;
  private Instructor instructor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(int aTaWorkHours, int aGraderWorkHours, int aCoursCredit, String aCourseId, int aStudentsEnroled, int aBudget, JobManager aJobManager, Instructor aInstructor)
  {
    taWorkHours = aTaWorkHours;
    graderWorkHours = aGraderWorkHours;
    coursCredit = aCoursCredit;
    courseId = aCourseId;
    studentsEnroled = aStudentsEnroled;
    budget = aBudget;
    job = new ArrayList<JobOffer>();
    boolean didAddJobManager = setJobManager(aJobManager);
    if (!didAddJobManager)
    {
      throw new RuntimeException("Unable to create course due to jobManager");
    }
    boolean didAddInstructor = setInstructor(aInstructor);
    if (!didAddInstructor)
    {
      throw new RuntimeException("Unable to create coursesTaught due to instructor");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTaWorkHours(int aTaWorkHours)
  {
    boolean wasSet = false;
    taWorkHours = aTaWorkHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setGraderWorkHours(int aGraderWorkHours)
  {
    boolean wasSet = false;
    graderWorkHours = aGraderWorkHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setCoursCredit(int aCoursCredit)
  {
    boolean wasSet = false;
    coursCredit = aCoursCredit;
    wasSet = true;
    return wasSet;
  }

  public boolean setCourseId(String aCourseId)
  {
    boolean wasSet = false;
    courseId = aCourseId;
    wasSet = true;
    return wasSet;
  }

  public boolean setStudentsEnroled(int aStudentsEnroled)
  {
    boolean wasSet = false;
    studentsEnroled = aStudentsEnroled;
    wasSet = true;
    return wasSet;
  }

  public boolean setBudget(int aBudget)
  {
    boolean wasSet = false;
    budget = aBudget;
    wasSet = true;
    return wasSet;
  }

  public int getTaWorkHours()
  {
    return taWorkHours;
  }

  public int getGraderWorkHours()
  {
    return graderWorkHours;
  }

  public int getCoursCredit()
  {
    return coursCredit;
  }

  public String getCourseId()
  {
    return courseId;
  }

  public int getStudentsEnroled()
  {
    return studentsEnroled;
  }

  public int getBudget()
  {
    return budget;
  }

  public JobOffer getJob(int index)
  {
    JobOffer aJob = job.get(index);
    return aJob;
  }

  public List<JobOffer> getJob()
  {
    List<JobOffer> newJob = Collections.unmodifiableList(job);
    return newJob;
  }

  public int numberOfJob()
  {
    int number = job.size();
    return number;
  }

  public boolean hasJob()
  {
    boolean has = job.size() > 0;
    return has;
  }

  public int indexOfJob(JobOffer aJob)
  {
    int index = job.indexOf(aJob);
    return index;
  }

  public JobManager getJobManager()
  {
    return jobManager;
  }

  public Instructor getInstructor()
  {
    return instructor;
  }

  public static int minimumNumberOfJob()
  {
    return 0;
  }

  public JobOffer addJob(int aWorkHours, String aReview, int aAcceptedApplicantId)
  {
    return new JobOffer(aWorkHours, aReview, aAcceptedApplicantId, this);
  }

  public boolean addJob(JobOffer aJob)
  {
    boolean wasAdded = false;
    if (job.contains(aJob)) { return false; }
    Course existingCourse = aJob.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aJob.setCourse(this);
    }
    else
    {
      job.add(aJob);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJob(JobOffer aJob)
  {
    boolean wasRemoved = false;
    //Unable to remove aJob, as it must always have a course
    if (!this.equals(aJob.getCourse()))
    {
      job.remove(aJob);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addJobAt(JobOffer aJob, int index)
  {  
    boolean wasAdded = false;
    if(addJob(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJob()) { index = numberOfJob() - 1; }
      job.remove(aJob);
      job.add(index, aJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobAt(JobOffer aJob, int index)
  {
    boolean wasAdded = false;
    if(job.contains(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJob()) { index = numberOfJob() - 1; }
      job.remove(aJob);
      job.add(index, aJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobAt(aJob, index);
    }
    return wasAdded;
  }

  public boolean setJobManager(JobManager aJobManager)
  {
    boolean wasSet = false;
    if (aJobManager == null)
    {
      return wasSet;
    }

    JobManager existingJobManager = jobManager;
    jobManager = aJobManager;
    if (existingJobManager != null && !existingJobManager.equals(aJobManager))
    {
      existingJobManager.removeCourse(this);
    }
    jobManager.addCourse(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setInstructor(Instructor aInstructor)
  {
    boolean wasSet = false;
    if (aInstructor == null)
    {
      return wasSet;
    }

    Instructor existingInstructor = instructor;
    instructor = aInstructor;
    if (existingInstructor != null && !existingInstructor.equals(aInstructor))
    {
      existingInstructor.removeCoursesTaught(this);
    }
    instructor.addCoursesTaught(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (job.size() > 0)
    {
      JobOffer aJob = job.get(job.size() - 1);
      aJob.delete();
      job.remove(aJob);
    }
    
    JobManager placeholderJobManager = jobManager;
    this.jobManager = null;
    placeholderJobManager.removeCourse(this);
    Instructor placeholderInstructor = instructor;
    this.instructor = null;
    placeholderInstructor.removeCoursesTaught(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "taWorkHours" + ":" + getTaWorkHours()+ "," +
            "graderWorkHours" + ":" + getGraderWorkHours()+ "," +
            "coursCredit" + ":" + getCoursCredit()+ "," +
            "courseId" + ":" + getCourseId()+ "," +
            "studentsEnroled" + ":" + getStudentsEnroled()+ "," +
            "budget" + ":" + getBudget()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "jobManager = "+(getJobManager()!=null?Integer.toHexString(System.identityHashCode(getJobManager())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "instructor = "+(getInstructor()!=null?Integer.toHexString(System.identityHashCode(getInstructor())):"null")
     + outputString;
  }
}