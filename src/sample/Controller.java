/**
 * Controller.java
 * Veronica Child and Adam Tigar
 *
 * Handles key inputs and the light cycle, scoreboard,
 * and grid updates of the TRON: Lightcycle game, .
 */

package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.Timer;
import java.util.TimerTask;


public class Controller implements EventHandler<KeyEvent>{
    final private double framesPerSecond = 20.0;

    @FXML private Button playButton;
    @FXML private Button resetButton;
    @FXML private Lightcycle playerOne;
    @FXML private Lightcycle playerTwo;
    @FXML private Grid theGrid;

    private boolean paused;
    private boolean crashed;
    public Scoreboard scoreBoard;
    private Timer timer;

    public Controller(){
        this.paused = false;
        this.crashed = false;
    }

    /**
     * Initializes game running, starts with game paused.
     */
    public void initialize(){
        this.setUpAnimationTimer();
        this.pauseGame();
    }

    /**
     * Sets up run timer for game animation
     */
    private void setUpAnimationTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateAnimation();
                    }
                });
            }
        };

        final long startTimeInMilliseconds = 0;
        final long repetitionPeriodInMilliseconds = 100;
        long frameTimeInMilliseconds = (long)(1000.0 / framesPerSecond);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    /**
     * Updates lightcycle movement, the Grid's jet wall placement, and
     * the Scoreboard's score. Also checks if any collisions occured.
     */
    private void updateAnimation() {
        // Calls grid to update jet wall placement
        this.playerOne.addCurrentPosition(this.playerOne.getLayoutX(), this.playerOne.getLayoutY());
        this.playerTwo.addCurrentPosition(this.playerTwo.getLayoutX(), this.playerTwo.getLayoutY());
        Rectangle jetWallPiece1 = this.playerOne.getCurrentPosition();
        Rectangle jetWallPiece2 = this.playerTwo.getCurrentPosition();

        this.theGrid.drawJetWall(Color.RED, jetWallPiece1);
        this.theGrid.drawJetWall(Color.ALICEBLUE, jetWallPiece2);

        this.playerOne.step();
        this.playerTwo.step();

        // Check for collisions and a potential winner
        boolean crash1 = this.playerOne.checkCrash(this.playerTwo.getPastPositions());
        boolean crash2 = this.playerTwo.checkCrash(this.playerOne.getPastPositions());
        if (!crash1) {
            this.scoreBoard.increaseTwoScore();
            this.theGrid.drawCrashMenu(1);
            this.playAgain();
        }
        if (!crash2) {
            this.scoreBoard.increaseOneScore();
            this.theGrid.drawCrashMenu(2);
            this.playAgain();
        }
        if (this.scoreBoard.winDetectOne()){
            this.theGrid.drawCrashMenu(3);
            this.playAgain();
        }
        if (this.scoreBoard.winDetectTwo()){
            this.theGrid.drawCrashMenu(4);
            this.playAgain();
        }
    }

    /**
     * Implements keystroke input to move light cycles.
     * @param keyEvent  Input from keyboard
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();

        if (!this.paused) {
            //WASD movement for player one
            if (code == KeyCode.A) {
                // Prevents player from turning 180 degrees
                if (this.playerOne.getVelocityX() != 5) {
                    this.playerOne.setVelocityX(-5);
                    this.playerOne.setVelocityY(0);
                    this.playerOne.setRotate(90);
                    this.playerOne.setDirection(4);
                }
                keyEvent.consume();
            } else if (code == KeyCode.D) {
                if (this.playerOne.getVelocityX() != -5) {
                    this.playerOne.setVelocityX(5);
                    this.playerOne.setVelocityY(0);
                    this.playerOne.setRotate(90);
                    this.playerOne.setDirection(2);
                }
                keyEvent.consume();
            } else if (code == KeyCode.W) {
                if (this.playerOne.getVelocityY() != 5) {
                    this.playerOne.setVelocityX(0);
                    this.playerOne.setVelocityY(-5);
                    this.playerOne.setRotate(0);
                    this.playerOne.setDirection(1);
                }
                keyEvent.consume();
            } else if (code == KeyCode.S) {
                if (this.playerOne.getVelocityY() != -5) {
                    this.playerOne.setVelocityX(0);
                    this.playerOne.setVelocityY(5);
                    this.playerOne.setRotate(0);
                    this.playerOne.setDirection(3);
                }
                keyEvent.consume();
            // Arrow key movement for player two
            } else if (code == KeyCode.LEFT) {
                if (this.playerTwo.getVelocityX() != 5) {
                    this.playerTwo.setVelocityX(-5);
                    this.playerTwo.setVelocityY(0);
                    this.playerTwo.setRotate(90);
                    this.playerTwo.setDirection(4);
                }
                keyEvent.consume();
            } else if (code == KeyCode.RIGHT) {
                if (this.playerTwo.getVelocityX() != -5) {
                    this.playerTwo.setVelocityX(5);
                    this.playerTwo.setVelocityY(0);
                    this.playerTwo.setRotate(90);
                    this.playerTwo.setDirection(2);
                }
                keyEvent.consume();
            } else if (code == KeyCode.UP) {
                if (this.playerTwo.getVelocityY() != 5) {
                    this.playerTwo.setVelocityX(0);
                    this.playerTwo.setVelocityY(-5);
                    this.playerTwo.setRotate(0);
                    this.playerTwo.setDirection(1);
                }
                keyEvent.consume();
            } else if (code == KeyCode.DOWN) {
                if (this.playerTwo.getVelocityY() != -5) {
                    this.playerTwo.setVelocityX(0);
                    this.playerTwo.setVelocityY(5);
                    this.playerTwo.setRotate(0);
                    this.playerTwo.setDirection(3);
                }
                keyEvent.consume();
            } else if (code == KeyCode.SPACE) {
                if (!this.crashed) {
                    this.pauseGame();
                } else{
                    this.playButton.disarm();
                }
                keyEvent.consume();
            }
        }
    }

    /**
     * Pauses or unpauses the game
     */
    private void pauseGame(){
        if (this.paused) {
            this.setUpAnimationTimer();
            this.playButton.setText("Pause");
        } else {
            this.timer.cancel();
            this.playButton.setText("Resume");
        }
        this.paused = !this.paused;
    }

    /**
     * Alters play button after a crash to read
     * "Play On" if no victor or hides play button
     * if a victor is present.
     */
    private void playAgain(){
        if (this.scoreBoard.winDetectOne()
                || this.scoreBoard.winDetectTwo()) {
            this.playButton.setVisible(false);
            this.resetButton.isFocused();
            this.timer.cancel();
        } else {
            this.playButton.setText("Play On!");
            this.timer.cancel();
        }
        this.crashed = true;
    }

    /**
     * Play button that either pauses or unpauses the game.
     * Following a crash with no declared winner, will reset the board.
     * @param actionevent   Input from mouseclick on button or spacebar
     */
    public void onPlayButton(ActionEvent actionevent) {
        // Automatically resets board following a crash
        if (this.crashed) {
            this.theGrid.resetBoard();

            this.playerOne.setLayoutX(300);
            this.playerOne.setLayoutY(465);
            this.playerOne.setVelocityX(0);
            this.playerOne.setVelocityY(-5);
            this.playerOne.setDirection(1);
            this.playerOne.clearAllPositions();

            this.playerTwo.setLayoutX(100);
            this.playerTwo.setLayoutY(100);
            this.playerTwo.setVelocityX(0);
            this.playerTwo.setVelocityY(5);
            this.playerTwo.setDirection(1);
            this.playerTwo.clearAllPositions();

            this.playButton.setText("Resume");
            this.crashed = false;
            this.paused = true;
        // If no player crashed, then either pauses or unpauses game
        } else {
            this.pauseGame();
        }
        this.playButton.isFocused();
        actionevent.consume();
    }

    /**
     * Reset button that resets the board and current score.
     * @param actionevent   Input from mouse click on button
     */
    public void onResetButton(ActionEvent actionevent) {
        this.paused = false;
        this.pauseGame();
        this.theGrid.resetBoard();
        this.scoreBoard.resetScore();

        this.playerOne.setLayoutX(300);
        this.playerOne.setLayoutY(465);
        this.playerOne.setVelocityX(0);
        this.playerOne.setVelocityY(-5);
        this.playerOne.setDirection(1);
        this.playerOne.clearAllPositions();

        this.playerTwo.setLayoutX(100);
        this.playerTwo.setLayoutY(100);
        this.playerTwo.setVelocityX(0);
        this.playerTwo.setVelocityY(5);
        this.playerTwo.setDirection(1);
        this.playerTwo.clearAllPositions();

        if (this.crashed) {
            this.playButton.setVisible(true);
        }
        this.crashed = false;
        this.playButton.requestFocus();
        actionevent.consume();
    }

    /**
     * Quit button that quits the entire application.
     * @param actionEvent   Input from mouse click on button
     */
    public void onQuit(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
}

