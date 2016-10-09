/**
 * Lightcycle.java
 * Veronica Child and Adam Tigar
 *
 * Model of lightcycle that contains movement methods, coordinates for jet wall
 * placement, and collision detection.
 */

package sample;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Bounds;

public class Lightcycle extends Rectangle {
    @FXML private double velocityX;
    @FXML private double velocityY;

    private ArrayList<Rectangle> allCoords = new ArrayList<Rectangle>();
    private int direction;
    private boolean alive;

    public Lightcycle() {
        this.setWidth(15);
        this.setHeight(15);
        this.direction = 1;
        this.alive = true;
    }

    /**
     * Adds current position on board to a list and generates a jet wall
     * node from that position.
     * @param x     Double x coordinate on board
     * @param y     Double y coordinate on board
     */
    public void addCurrentPosition(double x, double y) {
        Rectangle jetWall = new Rectangle();
        // Draws jet wall dimensions according to direction that
        // the lightcycle is moving in.
        // Moving Up
        if (this.direction == 1) {
            jetWall.setY(y + 10);
            jetWall.setX(x);
            jetWall.setWidth(15);
            jetWall.setHeight(5);
        //Right
        } else if (this.direction == 2) {
            jetWall.setX(x);
            jetWall.setY(y);
            jetWall.setWidth(5);
            jetWall.setHeight(15);
        //Down
        } else if (this.direction == 3){
            jetWall.setX(x);
            jetWall.setY(y);
            jetWall.setWidth(15);
            jetWall.setHeight(5);
        //Left
        } else {
            jetWall.setX(x + 10);
            jetWall.setY(y);
            jetWall.setWidth(5);
            jetWall.setHeight(15);
        }
        this.allCoords.add(jetWall);
    }

    /**
     * Gets lightcycle's current position on board.
     * @return  Rectangle with x and y coordinate of lightcycle's position
     */
    public Rectangle getCurrentPosition() {
        return this.allCoords.get(allCoords.size() - 1);
    }

    /**
     * Gets list of all coordinates lightcycle has been.
     * @return  ArrayList<Rectangle> of where lightcycle has been
     */
    public ArrayList<Rectangle> getPastPositions() {
        return this.allCoords;
    }

    /**
     * Sets movement direction of lightcycle
     * @param newDirection      Integer designating lightcycle's new direction
     */
    public void setDirection(int newDirection) {
        this.direction = newDirection;
    }

    /**
     * Clears all of lightcycle's past positions
     */
    public void clearAllPositions(){
        this.allCoords.clear();
    }

    /**
     * Step function to move lightcyle based on velocity and position
     */
    public void step() {
        this.setLayoutX(this.getLayoutX() + this.velocityX);
        this.setLayoutY(this.getLayoutY() + this.velocityY);
    }

    /**
     * Gets lightcycle's horizontal velocity
     * @return      Double of horizontal velocity
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * Sets lightcycle's horizontal velocity
     * @param velocityX     Double of current new horizontal velocity
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Gets lightcycle's vertical velocity
     * @return      Double of current vertical velocity
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Sets lightcycle's vertical velocity
     * @param velocityY     Double of current new veritcal velocity
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Check if lightcycle has crashed into grid walls or a jet wall
     * @param opponentJetWalls  ArrayList<Rectangle> of other player's jet walls
     * @return                  Boolean if lightcycle has crashed or not
     */
    public boolean checkCrash(ArrayList<Rectangle> opponentJetWalls) {
        this.alive = true;

        double top = this.getLayoutY() + this.getY();
        double side = this.getLayoutX() + this.getX();
        Bounds cycleBounds = this.getBoundsInParent();

        // Check if crash on left or right side of grid
        if (side <= 4 || side >= 583) {
            return !this.alive;
        // Check if crashed on top or bottom of grid
        } else if (top <=0 || top >= 485) {
            return !this.alive;
        } else {
            //Check if crashed in other player's jet walls
            for (Rectangle j : opponentJetWalls) {
                Bounds otherJetBounds = j.getBoundsInParent();
                if (cycleBounds.intersects(otherJetBounds)) {
                    return !this.alive;
                }
            }
            //Check if crashed in own jet walls
            for (int i = 0; i < this.getPastPositions().size() - 6; i++) {
                Rectangle j = this.getPastPositions().get(i);
                Bounds jetBounds = j.getBoundsInParent();
                if (cycleBounds.intersects(jetBounds)) {
                    return !this.alive;
                }
            }
        }
        return this.alive;
    }
}
