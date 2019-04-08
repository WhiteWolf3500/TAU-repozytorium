Scenario: Player deletes their character
  Given there is an existing player list
  When I try to delete a player with data <a> and <b> and <c>
  Then that player should not exist
  But list size won't change
Examples:
  |  a     |  b |  c      |
  |  Eliza |  1 | Archer  |
  |  Test  |  5 | Paladin |