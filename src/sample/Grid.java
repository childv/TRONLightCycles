/**
 * Grid.java
 * Veronica Child and Adam Tigar
 *
 * Generates game board for TRON and handles the
 * creation of Jet Walls and winner or loser notification.
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Grid extends Pane{
    @FXML private Label whoCrashed;

    private ArrayList<Rectangle> jetWallList;
    private Rectangle menuBack;

    public Grid(){
        this.jetWallList = new ArrayList<Rectangle>();

        this.menuBack = new Rectangle(100, 140, 400, 200);
        this.menuBack.setFill(Color.BLACK);
        this.menuBack.setArcWidth(20);
        this.menuBack.setArcHeight(20);

        this.whoCrashed = new Label();
        this.whoCrashed.setLayoutX(120);
        this.whoCrashed.setLayoutY(220);
        this.whoCrashed.setStyle("-fx-font-size: 35px;" +
                "-fx-font-family:'Open Sans Light'; -fx-font-weight:bold");
    }

    /**
     * Draws the player's jet walls
     * @param color     Color of jet walls that corresponds with the player
     * @param jetWall   Rectangle that contains the position of the jet wall
     */
    public void drawJetWall(Color color, Rectangle jetWall){
        jetWall.setFill(color);
        this.jetWallList.add(jetWall);
        this.getChildren().add(jetWall);
    }

    /**
     * Removes existing notifications and jet walls
     */
    public void resetBoard(){
        this.getChildren().removeAll(this.menuBack, this.whoCrashed);
        for (Rectangle j : this.jetWallList) {
            this.getChildren().remove(j);
        }
        this.jetWallList.clear();
    }

    /**
     * Draws a notification declaring who crashed or is victorious
     * depending on the menuCode.
     * @param menuCode  an integer designating which notification to display
     */
    public void drawCrashMenu(int menuCode){
        this.getChildren().removeAll(this.menuBack, this.whoCrashed);
        if (menuCode == 1) {
            this.whoCrashed.setText("Player One Crashed!");
            this.whoCrashed.setTextFill(Color.CRIMSON);
        } else if (menuCode == 2) {
            this.whoCrashed.setText("Player Two Crashed!");
            this.whoCrashed.setTextFill(Color.CORNFLOWERBLUE);
        } else if (menuCode == 3) {
            this.whoCrashed.setText("  Player One Wins!");
            this.whoCrashed.setTextFill(Color.CRIMSON);
        } else if (menuCode == 4) {
            this.whoCrashed.setText("  Player Two Wins!");
            this.whoCrashed.setTextFill(Color.CORNFLOWERBLUE);
        }
        this.getChildren().add(this.menuBack);
        this.getChildren().add(this.whoCrashed);
    }
}
