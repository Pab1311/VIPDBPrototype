public class Member {
  private String UIN;
  private String name;
  private int volunteerHours;

  Member(String UIN, String name, int volunteerHours){
    this.UIN = UIN;
    this.name = name;
    this.volunteerHours = volunteerHours;
  }

  Member(String UIN){
    this.UIN = UIN;
  }

  public String getName(){
    return name;
  }

  public int getVolunteerHours(){
    return volunteerHours;
  }

  public void setName(String newName){
    name = newName;
  }

  public void setVolunteerHours(int newVolunteerHours){
    volunteerHours = newVolunteerHours;
  }

  public void setUIN(String newUIN){
    UIN = newUIN;
  }

  public String getUIN(){
    return UIN;
  }

  public String getUIN(String studentName){
    if (studentName.equals(name)){
      return UIN;
    } else{
      System.out.println("UIN not found");
    }

    return null;
  }
}
