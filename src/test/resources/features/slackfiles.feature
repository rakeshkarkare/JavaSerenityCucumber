Feature: Scenarios to test Slack file features

  @regression
  Scenario Outline: Verify a user able to get files info from a slack channel
    When User sends a get request to fetch Info of file <File ID> from a channel
    Then User should validate HTTP 200 response code
    And User validates response of file <File ID> info from slack
    Examples:
      | File ID     |
      | F026UC1J7GV |
      | F026UEX6DGV |

  @regression
  Scenario Outline: Verify a user gets error message if files deleted from slack channel
    When User sends a get request to fetch Info of file <File ID> from a channel
    Then User should validate HTTP 200 response code
    And User validates error message <ERROR_MSG> from response
    Examples:
      | File ID     | ERROR_MSG |
      | F027M41U7DW | file_deleted |

  @regression
  Scenario Outline: Verify a user gets error message if auth is invalid
    When User sends a get request to fetch Info of file from a channel with invalid auth
    Then User should validate HTTP 200 response code
    And User validates error message <ERROR_MSG> from response
    Examples:
      | ERROR_MSG    |
      | invalid_auth |

  @regression
  Scenario Outline: Verify a user gets error message if files is invalid
    When User sends a get request to fetch Info of file <File ID> from a channel
    Then User should validate HTTP 200 response code
    And User validates error message <ERROR_MSG> from response
    Examples:
      | File ID      | ERROR_MSG    |
      | invalid_file | file_not_found |
