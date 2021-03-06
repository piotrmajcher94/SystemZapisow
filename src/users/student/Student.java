package users.student;
import exceptions.NotIntheGroupException;
import exceptions.WrongGroupException;
import javafx.collections.FXCollections;
import sample.*;
import users.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import javafx.collections.ObservableList;

/**
 * Created by piotrek on 06.12.16.
 */
public class Student extends User {
    //atrybuty studenta
    private String ID;
    private Department department;
    private String departmentName;
    private FieldsOfStudies fieldOfStudy;
    private String fieldOfStudyName;
    private Specialization specialization;
    private String specializationName;
    private int term;
    private Boolean signUpRight;
    private int ECTS;
    private ObservableList<Group> groupList = FXCollections.observableArrayList();

    /*public Student(String login, String password, String name, String surname, String email, String ID, Department department, FieldsOfStudies fieldOfStudy, Specialization specialization, int term, boolean signUpRight, int ECTS){
        this.login = login;
        this.password = password;
        this. name = name;
        this.surname = surname;
        this.email = email;
        this.ID = ID;
        this.department = department;
        this.departmentName = this.department.getName();
        this.fieldOfStudy = fieldOfStudy;
        this.fieldOfStudyName = this.fieldOfStudy.getName();
        this.specialization = specialization;
        this.specializationName = this.specialization.getName();
        this.term = term;
        this.signUpRight = signUpRight;
        this.ECTS = ECTS;
        this.groupList = FXCollections.observableArrayList();
    }*/

    public Student(){

        this.login = null;
        this.password = null;
        this. name = null;
        this.surname = null;
        this.email = null;
        this.ID = null;
        this.department = null;
        this.departmentName = null;
        this.fieldOfStudy = null;
        this.fieldOfStudyName = null;
        this.specialization = null;
        this.specializationName = null;
        this.term = -1;
        this.signUpRight = null;
        this.ECTS = -1;
        this.groupList = FXCollections.observableArrayList();
    }

    public ObservableList<Group> getGroupList() {return groupList;}

    public void signUpToGroup(Group group) throws WrongGroupException {

        if(this.signUpRight){

            if (group.getAvaiablePlaces() > 0 ) {

                if(this.getGroupList().size() > 0) {
                    for (Group g : this.groupList) {
                        if (group.isInTheGroup(this))
                            throw new WrongGroupException("Zostałeś już zapisany do tej grupy");

                        if (g.getCourseCode().equals(group.getCourseCode()) && g.getType().equals(group.getType()))
                            throw new WrongGroupException("Jeseś już zapisany do grupy tego typu w wybranym kursie.");
                    }
                }

                    //dodanie studenta dla obiektu grupy w bazie danych

                if(!group.getDepartment().equals(this.getDepartment())
                        || !group.getFieldOfStudy().equals(this.getFieldOfStudy())
                        || !(group.getSpecialization().equals(this.getSpecialization()) || group.getSpecialization().equals(Specialization.NOSPECIALIZATION))
                        || group.getTerm()!= this.getTerm())
                    throw new WrongGroupException("Zła grupa! Nie możesz się do niej zapisać!");

                    group.addStudent(this);
                    //dodanie studentowi grupy
                    this.groupList.add(group);


            }else
                throw new WrongGroupException("W grupie nie ma juz miejsc!");
        }else
            throw new WrongGroupException("Nie posiadasz prawa do zapisów.");


    }

    public void signOutFromGroup(Group group) throws NotIntheGroupException {
      boolean isIntheGroup = false;
      for(Student s : group.getSignedUpStudents())
          if(s.equals(this)) {
              isIntheGroup = true;
              break;
          }
      if(!isIntheGroup)
          throw new NotIntheGroupException("Studenta nie ma w tej grupie");

      group.getSignedUpStudents().remove(this);
      group.incAvaiablePlaces();
      this.groupList.remove(group);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public FieldsOfStudies getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldsOfStudies fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public boolean getSignUpRight() {
        return signUpRight;
    }

    public void setSignUpRight(boolean signUpRight) {
        this.signUpRight = signUpRight;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public String getDepartmentName() { return departmentName; }

    public String getFieldOfStudyName() { return fieldOfStudyName; }

    public String getSpecializationName() { return specializationName; }

    @Override
    public ArrayList<String> getProfile(){
        ArrayList<String> profileData = new ArrayList<>();

        profileData.add(login);
        profileData.add(password);
        profileData.add(name);
        profileData.add(surname);
        profileData.add(email);
        profileData.add(ID);
        profileData.add(department.getName());
        profileData.add(fieldOfStudy.getName());
        profileData.add(specialization.getName());
        profileData.add(String.valueOf(term));
        profileData.add(String.valueOf(signUpRight));
        profileData.add(String.valueOf(ECTS));
        return profileData;
    }

    @Override
    public String toString(){
        String result = "";
        result += login + " ";
        result += password + " ";
        result += name + " ";
        result += surname + " ";
        result += email + " ";
        result += ID + " ";
        result += department + " ";
        result += fieldOfStudy + " ";
        result += specialization + " ";
        result += term + " ";
        result += signUpRight + " ";
        result += ECTS + " ";

        return result;
    }


}
