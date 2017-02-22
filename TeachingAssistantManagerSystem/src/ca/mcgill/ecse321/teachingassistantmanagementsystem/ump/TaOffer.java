/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ca.mcgill.ecse321.teachingassistantmanagementsystem.ump;
import java.util.*;

// line 41 "../../../../../TeachingAssistantManagementSystem.ump"
public class TaOffer extends JobOffer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TaOffer Attributes
  private int capacity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TaOffer(int aWorkHours, Course aCourse, int aCapacity)
  {
    super(aWorkHours, aCourse);
    capacity = aCapacity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCapacity(int aCapacity)
  {
    boolean wasSet = false;
    capacity = aCapacity;
    wasSet = true;
    return wasSet;
  }

  public int getCapacity()
  {
    return capacity;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "capacity" + ":" + getCapacity()+ "]"
     + outputString;
  }
}