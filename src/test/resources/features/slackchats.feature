Feature: Scenarios to test Slack chat messages on channel

  @sanity @regression
  Scenario: Verify a user able to share a me message into a slack channel
    When User share a chat me message to channel
    Then User should validate HTTP 200 response code
    And User validates the response of chat me message from slack channel

  @sanity @regression
  Scenario Outline: Verify a error message for chat me api with invalid token
    When User check chat me with invalid token
    Then User should validate HTTP 200 response code
    And User validates error message <ERROR_MSG> from response
    Examples:
      | ERROR_MSG    |
      | invalid_auth |

  @sanity @regression
  Scenario: Verify a user is able to sends an ephemeral message to a user in a channel
    Given User sends post request to auth test to get user id
    Then User validates the response of auth test from slack channel
    And User should validate HTTP 200 response code
    When User sends an ephemeral message to a user in a channel
    Then User should validate HTTP 200 response code
    And User validates the response of ephemeral message from slack channel

  @sanity @regression
  Scenario Outline: Verify ephemeral message not send in channel for user not present
    When User sends an ephemeral message to a user not present in a channel
    Then User should validate HTTP 200 response code
    And User validates error message <ERROR_MSG> from response
    Examples:
      | ERROR_MSG    |
      | user_not_in_channel |