Feature: Get the details from cricbuzz
  @req
  Scenario Outline: Get the request and response of users
    Given user hit the api for creating user as "<name>" and "<job>"
    When user get the user_id than frame the get Request

    Examples:
      | name | job     |
      | soma | it      |


