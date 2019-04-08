Feature: Adding a new Player
  Player adds their character

  Scenario Outline: Player adds their character
    Given there is a player object
    And I type name "<a>"
    And I type level <b>
    And I type heroclass "<c>"
    When I send this data
    Then the list should be <list>
  Examples:
    |  a     |  b |  c     | list |
    |  Adam  |  1 | Knight |    1 |
    |  Eliza |  3 | Archer |    0 |