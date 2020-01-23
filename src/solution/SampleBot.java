package solution;

import battleship.BattleShip;
import battleship.CellState;
import java.awt.Point;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * A Sample random shooter - Takes no precaution on double shooting and has no
 * strategy once a ship is hit.
 *
 * @author mark.yendt
 * 
 * Team:
 * Praditya Bhatt - 000773178
 * 
 * Chirag Baranda - 000759867
 * 
 * Dhrumil Thaker - 000764979
 */
public class SampleBot {

    private int gameSize;
    private BattleShip battleShip;
    private Random random;
    public CellState board[][];   
    private Stack<Point> shots;
  
    /**
     * Constructor keeps a copy of the BattleShip instance
     *
     * @param b previously created battleship instance - should be a new game
     */
    public SampleBot(BattleShip b) {
        battleShip = b;
        gameSize = b.BOARDSIZE;
        board = new CellState[gameSize][gameSize];     
        shots = new Stack<Point>();
        CreateShotsPattern();
        random = new Random();   // Needed for random shooter - not required for more systematic approaches
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = CellState.Empty;
            }
        }
    }

    /*
       Hit shots in every other point
    */
    public void CreateShotsPattern() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                
                if ((i + j) % 2 == 0) {
                    Point shot = new Point(i, j);
                    shots.push(shot);
                }
            }
        }
    }

    /**
     * Create a random shot and calls the battleship shoot method
     *
     * @return true if a Ship is hit, false otherwise
     */
    public boolean fireShot() {

        //pops the point from the stack
        Point hitShot = shots.pop();
        
        boolean hit = false;
        // take into account the state of the shot that has been fired and decide wheather to fire the adjecent points
        if (board[(int) hitShot.getX()][(int) hitShot.getY()] == CellState.Empty) {
            hit = battleShip.shoot(hitShot);

            if (hit) {
                board[(int) hitShot.getX()][(int) hitShot.getY()] = CellState.Hit;
                
                reHit(hitShot);
            } else {
                board[(int) hitShot.getX()][(int) hitShot.getY()] = CellState.Miss;
            }
        }
        return hit;
    }

    public boolean SingleFireShot(Point hitShot) {

        boolean hit = false;

        if (board[(int) hitShot.getX()][(int) hitShot.getY()] == CellState.Empty) {
            hit = battleShip.shoot(hitShot);

            if (hit) {
                board[(int) hitShot.getX()][(int) hitShot.getY()] = CellState.Hit;
            } else {
                board[(int) hitShot.getX()][(int) hitShot.getY()] = CellState.Miss;
            }
        }
        return hit;
    }

//    public void printBoard() {
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board.length; j++) {
//                System.out.print(" " + board[i][j] + " ");
//            }
//            System.out.println("");
//        }
//    }
        
    private void reHit(Point hitShot) {

        //variables that will store the surrounding points when a Hit is recorded
        Point p1, p2, p3, p4;
        p1 = new Point((int) hitShot.getX() + 1, (int) hitShot.getY());
        p2 = new Point((int) hitShot.getX() - 1, (int) hitShot.getY());
        p3 = new Point((int) hitShot.getX(), (int) hitShot.getY() - 1);
        p4 = new Point((int) hitShot.getX(), (int) hitShot.getY() + 1);

        //cases for the corners and edges where there are less than 4 possiblites after a Hit is recorded
        if ((((int) hitShot.getX()) == 0 && ((int) hitShot.getY()) == 0)) {
            if (SingleFireShot(p1)) {
                reHit(p1);
            }
            if (SingleFireShot(p4)) {
                reHit(p4);
            }
        } else if (((int) hitShot.getX()) == 0 && ((int) hitShot.getY()) == 9) {
            if (SingleFireShot(p1)) {
                reHit(p1);
            }
            if (SingleFireShot(p3)) {
                reHit(p3);
            }
        } else if (((int) hitShot.getX()) == 9 && ((int) hitShot.getY()) == 0) {

            if (SingleFireShot(p2)) {
                reHit(p2);
            }
            if (SingleFireShot(p4)) {
                reHit(p4);
            }
        } else if (((int) hitShot.getX()) == 9 && ((int) hitShot.getY()) == 9) {

            if (SingleFireShot(p2)) {
                reHit(p2);
            }
            if (SingleFireShot(p3)) {
                reHit(p3);
            }
        } else if ((((int) hitShot.getX()) == 0 && ((int) hitShot.getY()) != 0)) {

            if (SingleFireShot(p1)) {
                reHit(p1);
            }
            if (SingleFireShot(p3)) {
                reHit(p3);
            }
            if (SingleFireShot(p4)) {
                reHit(p4);
            }
        } else if ((((int) hitShot.getX()) != 0 && ((int) hitShot.getY()) == 0)) {

            if (SingleFireShot(p1)) {
                reHit(p1);
            }
            if (SingleFireShot(p2)) {
                reHit(p2);
            };
            if (SingleFireShot(p4)) {
                reHit(p4);
            }
        } else if ((((int) hitShot.getX()) == 9 && ((int) hitShot.getY()) != 9)) {

            if (SingleFireShot(p2)) {
                reHit(p2);
            }
            if (SingleFireShot(p3)) {
                reHit(p3);
            }
            if (SingleFireShot(p4)) {
                reHit(p4);
            }
        } else if ((((int) hitShot.getX()) != 9 && ((int) hitShot.getY()) == 9)) {

            if (SingleFireShot(p1)) {
                reHit(p1);
            }
            if (SingleFireShot(p2)) {
                reHit(p2);
            }
            if (SingleFireShot(p3)) {
                reHit(p3);
            }

        } else {

            if (SingleFireShot(p1)) {
                reHit(p1);
            }
            if (SingleFireShot(p2)) {
                reHit(p2);
            }
            if (SingleFireShot(p3)) {
                reHit(p3);
            }
            if (SingleFireShot(p4)) {
                reHit(p4);
            }
        }
    }
}
