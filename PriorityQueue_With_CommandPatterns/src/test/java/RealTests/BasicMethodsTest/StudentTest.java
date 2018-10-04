package RealTests.BasicMethodsTest;

import jsf.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentTest {
    private String name = "Name";
    private int redID = 123456789;
    private String email =  "Email";
    private int units = 100;
    private double gpa = 4.0;
    private int lowUnits = Student.MIN_UNITS - 1;
    private int highUnits = Student.MAX_UNITS + 1;
    private double lowGPA = Student.MIN_GPA - 0.1;
    private double highGPA = Student.MAX_GPA + 0.1;
    public static final String INVALID_STUDENT_CREATED =
            "An invalid Student was instantiated";

    @Test
    @DisplayName("Student Constructor")
    void studentConstructor(){
        Student student = new Student(name, redID, email, units, gpa);
        assertTrue(student.name.equals(name));
        assertTrue(student.redID == redID);
        assertTrue(student.email.equals(email));
        assertTrue(student.units == units);
        assertTrue(student.gpa == gpa);
        assertTrue(student.getPriority() ==
                student.gpa/Student.MAX_GPA*Student.GPA_RATE
                + student.units/Student.MAX_UNITS
                * Student.UNITS_RATE);
    }

    @Test
    @DisplayName("Invalid Student - low Units")
    void invalidLowUnits(){
        try{
            Student invalidStudent =
                    new Student (name, redID, email, lowUnits, gpa);
            fail(INVALID_STUDENT_CREATED);
        }
        catch(IllegalArgumentException e){
            assertTrue(e.getMessage().equals(Student.INVALID_UNITS_MESSAGE));
        }
    }

    @Test
    @DisplayName("Invalid Student - high Units")
    void invalidHighUnits(){
        try{
            Student invalidStudent =
                new Student (name, redID, email, highUnits, gpa);
            fail(INVALID_STUDENT_CREATED);
        }
        catch(IllegalArgumentException e){
            assertTrue(e.getMessage().
                equals(Student.INVALID_UNITS_MESSAGE));
        }
    }

    @Test
    @DisplayName("Invalid Student - low gpa")
    void invalidLowGPA(){
        try{
            Student invalidStudent = new Student (name, redID, email, units, lowGPA);
            fail(INVALID_STUDENT_CREATED);
        }
        catch(IllegalArgumentException e){
            assertTrue(e.getMessage().
                equals(Student.INVALID_GPA_MESSAGE));
        }
    }

    @Test
    @DisplayName("Invalid Student - high gpa")
    void invalidHighGPA(){
        try{
            Student invalidStudent =
                new Student (name, redID, email, units, highGPA);
            fail(INVALID_STUDENT_CREATED);
        }
        catch(IllegalArgumentException e){
            assertTrue(e.getMessage().
                equals(Student.INVALID_GPA_MESSAGE));
        }
    }

    @Test
    void compareToTest() {
        Student highPriority = new Student("HighPriorityTest",
                redID, email, Student.MAX_UNITS, Student.MAX_GPA);
        Student lowPriority = new Student("LowPriorityTest",
                redID, email, Student.MIN_UNITS, Student.MIN_GPA);
        Student duplicateHighPriority =
                new Student("DuplicateHighPriorityTest",
                redID, email, Student.MAX_UNITS, Student.MAX_GPA);
        Student duplicateLowPriority =
                new Student("DuplicateLowPriorityTest",
                redID, email, Student.MIN_UNITS, Student.MIN_GPA);
        assertTrue(highPriority.compareTo(lowPriority) == 1);
        assertTrue(lowPriority.compareTo(highPriority) == -1);
        assertTrue(highPriority.compareTo(duplicateHighPriority)==0);
        assertTrue(lowPriority.compareTo(duplicateLowPriority)==0);
    }

}