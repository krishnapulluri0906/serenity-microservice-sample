@userService @runAllServices
Feature: User Service Endpoints
  As a user, I want to check all endpoints of user service

  @manual
  @createUser
  Scenario Outline: Create User Records
    Given I want to test "<serviceName>" service
    When I do a POST request with user details like "<userName>","<userAddress>","<userAge>"
    Then I validate the response with statuscode "<expectedStatus>"

    Examples:
      | tc_id | serviceName | expectedStatus | userName | userAddress | userAge |
      | TC01  | createUser  | 200            | user1    | 1/2 Avenue  | 35      |
      | TC02  | createUser  | 200            | user2    | 1/4 Avenue  | 31      |


