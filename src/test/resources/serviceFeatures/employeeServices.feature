@employeeService @runAllServices
Feature: Employee Service Endpoints
  As a user, I want to check all endpoints of employee service

  @createEmp
  Scenario Outline: Create Employee Records
    Given I want to test "<serviceName>" service
    When I do a POST request with "<emp_name>","<emp_salary>","<emp_age>"
    Then I validate the response with statuscode "<expectedStatus>"

    Examples:
      | tc_id | serviceName | expectedStatus | emp_name | emp_salary | emp_age |
      | TC01  | create      | 200            | emp1     | 6000       | 35      |
      | TC02  | create      | 200            | emp2     | 2000       | 23      |
      | TC03  | create      | 200            | emp3     | 700        | 22      |
      | TC04  | create      | 400            | emp4     | 9000       | 45      |
      | TC05  | create      | 200            | emp5     | 12000      | 26      |
      | TC06  | create      | 200            | emp6     | 99000      | 36      |
  #-----------------------------------------------------------------------------------

  @getEmp
  Scenario Outline: Get Employee Records
    Given I want to test "<serviceName>" service
    When I do a GET request
    Then I validate the response with statuscode "<expectedStatus>"
    And I check number of employees returned

    Examples:
      | tc_id | serviceName | expectedStatus |
      | TC07  | employees   | 200            |

 #-----------------------------------------------------------------------------------

  @manual
  @deleteEmp
  Scenario Outline: Delete Employee Record
    Given I want to test "<serviceName>" service
    When I do a DELETE request for emp "<emp_name>"
    Then I validate the response with statuscode "<expectedStatus>"

    Examples:
      | tc_id | serviceName | emp_name | expectedStatus |
      | TC08  | delete      | emp200   | 200            |