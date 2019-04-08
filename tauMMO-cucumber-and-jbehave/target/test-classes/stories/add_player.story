Scenario: Player adds their character
  Given there is a player object
  And I type <a> and <b> and <c>
  When I send this data
  Then the list should be <list>
Examples:
  |  a     |  b |  c     | list |
  |  Adam  |  1 | Knight |    1 |
  |  Eliza |  3 | Archer |    0 |
