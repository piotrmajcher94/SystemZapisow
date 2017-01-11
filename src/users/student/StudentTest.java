package users.student;

import org.junit.Before;
import sample.*;

import static org.junit.Assert.*;

/**
 * Created by piotrek on 11.01.17.
 */
public class StudentTest {

    private Group group;
    private Student student;

    @Before
    public void init(){
       group = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "123",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        student = new Student();
        student.setSignUpRight(true);
        student.setSpecialization(Specialization.NOSPECIALIZATION);
        student.setDepartment(Department.W1);
        student.setFieldOfStudy(FieldsOfStudies.W1K1);
        student.setTerm(1);
    }

    @org.junit.Test
    public void signUpToGoodGroupTest() throws Exception {
        int availablePlaces = group.getAvaiablePlaces();
        student.signUpToGroup(group);
        assertTrue(student.getGroupList().get(0).equals(group) && availablePlaces-1 == group.getAvaiablePlaces());

    }

    @org.junit.Test
    public void signOutFromGoodGroupTest() throws Exception {

        student.signUpToGroup(group);
        int availablePlaces = group.getAvaiablePlaces();
        int studentGroupNum = student.getGroupList().size();
        student.signOutFromGroup(group);
        assertTrue((availablePlaces+1 == group.getAvaiablePlaces()) && (studentGroupNum-1 ==student.getGroupList().size()));

    }

}