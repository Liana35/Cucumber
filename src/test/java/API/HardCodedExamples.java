package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {


   String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
   String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ5NzE0NDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTAxNDY0MSwidXNlcklkIjoiNTIzMCJ9.8UGykUye6OI2zvEnlTeaJPMmRn9Bfy1rYAjCpCPI5Ww";

   static String employee_id;


   @Test
   public void bgetCreatedEmployee(){
      RequestSpecification preparedRequest=given().
              header("Content-Type","application/json").
              header("Authorization", token).
              queryParam("employee_id", employee_id);

      // hitting the endpoint
      Response response= preparedRequest.when().get("/getOneEmployee.php");
      response.prettyPrint();
      //verify the response
      response.then().assertThat().statusCode(200);
      String tempEmpId=response.jsonPath().getString("employee.employee_id");

      //we have 2 emp id , one is global and second is local
      Assert.assertEquals(employee_id,tempEmpId);
   }




   @Test
   public void acreateEmployee(){
       //prepare the request
      RequestSpecification preparedRequest= given().header("Content-Type","application/json").
              header("Authorization",token).body("{\n" +
                      "  \"emp_firstname\": \"mark\",\n" +
                      "  \"emp_lastname\": \"allen\",\n" +
                      "  \"emp_middle_name\": \"mrs\",\n" +
                      "  \"emp_gender\": \"M\",\n" +
                      "  \"emp_birthday\": \"2000-03-20\",\n" +
                      "  \"emp_status\": \"Confirmed\",\n" +
                      "  \"emp_job_title\": \"SDET\"\n" +
                      "}");

      // hitting the end point// send the request

       Response response= preparedRequest.when().post("/createEmployee.php");
      //verifying the assertions/ get response

      response.then().assertThat().statusCode(201);

      //it will print the output in the console
      response.prettyPrint();

      //we are capturing employee id from the response
      employee_id= response.jsonPath().getString("Employee.employee_id");
      System.out.println(employee_id);

      //verifying the first name in the response body
      response.then().assertThat().body("Employee.emp_firstname", equalTo("mark"));


      //verify the response headers
      response.then().assertThat().header("Content-Type","application/json");


      System.out.println("My test case is passed");
   }

   //in homework create a method to get all employee status and job title

   @Test
   public void cupdateEmployee(){
      RequestSpecification preparedRequest=given().
              header("Content-Type","application/json").
              header("Authorization",token).body("{\n" +
                      "  \"employee_id\": \""+employee_id+"\",\n" +
                      "  \"emp_firstname\": \"Olivia\",\n" +
                      "  \"emp_lastname\": \"Ellen\",\n" +
                      "  \"emp_middle_name\": \"msa\",\n" +
                      "  \"emp_gender\": \"F\",\n" +
                      "  \"emp_birthday\": \"2001-04-20\",\n" +
                      "  \"emp_status\": \"probation\",\n" +
                      "  \"emp_job_title\": \"CEO\"\n" +
                      "}");
      //hitting the endpoint
      Response response = preparedRequest.when().put("/updateEmployee.php");
      response.then().assertThat().statusCode(200);
      //for verification
      response.then().assertThat().body("Message", equalTo("Employee record Updated"));
   }

   @Test
   public void dgetUpdatedEmployee(){
      RequestSpecification preparedRequest = given().
              header("Content-Type","application/json").
              header("Authorization", token).
              queryParam("employee_id",employee_id);

      Response response = preparedRequest.when().get("/getOneEmployee.php");
      response.prettyPrint();
      response.then().assertThat().statusCode(200);
      //if you want to verify the body of the response.
      //you can do that using hamcrest matchers

   }
}
