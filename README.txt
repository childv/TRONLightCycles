TRON
by Veronica Child and Adam Tigar

Our TRON game is based on one of the four subgames of the TRON arcade game, in particular the Light Cycle subgame.
A variant of the snake arcade game, Light Cycles consist of two players guiding vehicles, called "Light Cycles",
around a board. As the Light Cycles move, they leave behind a lasting trail, referred to as "Jet Walls". A player
loses when they crash into the edge of the board or into the other player's Jet Wall. To win, players must avoid
crashing and maneuver instead to force the opposing player to crash.

Play mode is two controlled players, Player One using WASD keys and Player Two using the arrow keys.

To compile TRON on IntelliJ, use Main.java as the main function to run the game.

Main classes:
Lightcycle.java {
  List of sequence of previous and current positional coordinates
  Collision detection
  Current direction
  Change direction
  Step/move 1 unit
}

Scoreboard.java {
  Increase score
  Updates view on Scene
}

Grid.java {
  Placement of jetwalls
  Aesthetic notification regarding crashes and wins
}

SUPPLEMENT:
Our changing menu consists of changing the button text depending on in-game action. This is
not the most efficient way to do this; the better way would have been to create different screens
with their own controllers and new buttons depending on if player had crashed or won.

We also would have liked the jet walls to be outlined. This would make it easier for players
to move directly parallel to their jet wall and yet still see where they have been without
the board looking too messy.

We could have also better separated the different classes per MVC to make them more distinct.
Some of our classes serve as models and views, such as grid, which both draws the jet walls
and stores the data of their position.

Had we extra time, we would have liked to have added the option for the user to increase
their lightcycle's velocity momentarily. We also would have implemented a one player mode where
a player races against an AI. We also would have added a starting menu that welcomed players to the game.