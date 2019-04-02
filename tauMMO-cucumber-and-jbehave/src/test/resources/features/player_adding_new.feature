Feature: Adding a new Player
  Player adds their character

  Scenario Outline: Player adds their character
    Given there is a player object
    When data "<a>" and <b> and "<c>" are send
    Then the result should be <result>
  Examples:
    |  a     |  b |  c     | result |
    |  Adam  |  1 | Knight |      1 |
    |  Eliza |  3 | Archer |      1 |