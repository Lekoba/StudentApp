package com.studentapp.junit.studentInfo;

import com.studentapp.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class studentCRUDTest extends TestBase {

    static  int studentId;
    static String firstname = "SMOKEUSER"+ TestUtils.getRandomValue();
    static String lastname = "SMOKEUSER"+TestUtils.getRandomValue();
    static String  programme= "computerScience";
    static String email = TestUtils.getRandomValue()+"xyz@gmail.com";

    @Steps
    StudentSerenitySteps steps;
    @Title("This test will create a new student")
    @Test
    public void test001()
    {
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C++");
        steps.createStudent(firstname, lastname, email,programme,courses)
        .statusCode(201)
        .spec(ReusableSpecifications.getGenericResponseSpec());

    }

    @Title("Verify if the student was added to the application")
    @Test
    public void test002() {

        HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstname);
        System.out.println("The value is "+ value);
        assertThat(value, hasValue(firstname));
        studentId = (int) value.get("id");
    }

    @Title("Update the user information and verify the updated information!")
    @Test
    public void test003(){

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C++");

        firstname = firstname + "_Updated";

        steps.updateStudent(studentId, firstname, lastname,email,programme,courses);

        //Checking that it is updated
        HashMap <String, Object> value= steps.getStudentInfoByFirstName(firstname);

        System.out.println("The value is "+ value);

        assertThat(value, hasValue(firstname));

    }

    @Title("Delete the student and verify the student is deleted!")
    @Test
    public void test004(){

       steps.deleteStudent(studentId);
       steps.getStudentById(studentId).statusCode(404);
    }

}
