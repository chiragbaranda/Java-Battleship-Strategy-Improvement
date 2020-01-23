package solution;

import battleship.BattleShip;

/**
 * Starting code for Comp10152 - Lab#6
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
public class COMP10152_Lab6 {

    static final int NUMBEROFGAMES = 10000;

    public static void startingSolution() {
        int totalShots = 0;
        System.out.println(BattleShip.version());

        for (int game = 0; game < NUMBEROFGAMES; game++) {

            BattleShip battleShip = new BattleShip();
            SampleBot sampleBot = new SampleBot(battleShip);

            // Call SampleBot Fire randomly - You need to make this better!
            while (!battleShip.allSunk()) {
                sampleBot.fireShot();
            }
            int gameShots = battleShip.totalShotsTaken();
            totalShots += gameShots;
        }       
        System.out.printf("SampleBot - The Average # of Shots required in %d games to sink all Ships = %.2f\n", NUMBEROFGAMES, (double) totalShots / NUMBEROFGAMES);
    }

    public static void main(String[] args) {
        startingSolution();
    }
}
