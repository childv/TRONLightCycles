/**
 * Scoreboard.java
 * Veronica Child and Adam Tigar
 *
 * Keeps track of score for TRON:Lightcycle game
 * and updates side score view.
 */

package sample;

import javafx.scene.Group;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Scoreboard extends Group{
    @FXML private Label oneLabel;
    @FXML private Label twoLabel;

    private int scoreOne;
    private int scoreTwo;

    public Scoreboard(){
        this.scoreOne = 0;
        this.scoreTwo = 0;

        this.oneLabel = new Label();
        this.oneLabel.setStyle("-fx-font-size: 14");
        this.oneLabel.setLayoutY(20);
        this.oneLabel.setTextFill(Color.DARKRED);
        this.oneLabel.setText(String.format("Player One: %d", this.scoreOne));

        this.twoLabel= new Label();
        this.twoLabel.setStyle("-fx-font-size: 14");
        this.twoLabel.setLayoutY(45);
        this.twoLabel.setTextFill(Color.CORNFLOWERBLUE);
        this.twoLabel.setText(String.format("Player Two: %d", this.scoreTwo));

        this.getChildren().add(this.oneLabel);
        this.getChildren().add(this.twoLabel);
    }

    /**
     * Increases player two's score and updates view
     */
    public void increaseTwoScore(){
        this.scoreTwo++;
        this.getChildren().remove(this.twoLabel);
        this.twoLabel.setText(String.format("Player Two: %d", this.scoreTwo));
        this.getChildren().add(this.twoLabel);
    }

    /**
     * Increases player one's score and updates view
     */
    public void increaseOneScore() {
        this.scoreOne++;
        this.getChildren().remove(this.oneLabel);
        this.oneLabel.setText(String.format("Player One: %d", this.scoreOne));
        this.getChildren().add(this.oneLabel);
    }

    /**
     * Resets the current score for both player one and two
     */
    public void resetScore(){
        this.getChildren().removeAll(this.oneLabel, this.twoLabel);
        this.scoreOne = 0;
        this.scoreTwo = 0;
        this.oneLabel.setText(String.format("Player One: %d", this.scoreOne));
        this.twoLabel.setText(String.format("Player Two: %d", this.scoreTwo));
        this.getChildren().addAll(this.oneLabel, this.twoLabel);
    }

    /**
     * Checks score to see if player one has won
     * @return Boolean if player one won (true) or not (false)
     */
    public boolean winDetectOne() {
        if (this.scoreOne >= 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks score to see if player two has won
     * @return Boolean if player won (true) or not (false)
     */
    public boolean winDetectTwo() {
        if (this.scoreTwo >= 3) {
            return true;
        } else {
            return false;
        }
    }

}
