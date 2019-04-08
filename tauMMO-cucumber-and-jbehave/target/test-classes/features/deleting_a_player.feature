Feature: Deleting a Player
  Deletes a Player character

  Scenario Outline: Player deletes their character
    Given there is an existing player list
    When I delete a player with data "<a>" and <b> and "<c>"
    Then that player should not exist <deleted>
    But list size won't change
  Examples:
    |  a     |  b |  c      | deleted |
    |  Eliza |  1 | Archer  |     -1  |
    |  Test  |  5 | Paladin |     -1  |