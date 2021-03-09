package PlayerManagement.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Dice.Dice;
import PlayerManagement.Player;

public class PlayerRollTransaction {

    public List<Dice> gameDice = new ArrayList<Dice>();
    public Random num = new Random();

    public List<Dice> roll(Player p) {

        int dice1 = num.nextInt(6) + 1;
        int dice2 = num.nextInt(6) + 1;

        if (dice1 == dice2) {
            gameDice.add(new Dice(dice1, dice2, true));
            System.out.println(p.getName() + " has rolled " + dice1 + ", " + dice2);
        } else {
            gameDice.add(new Dice(dice1, dice2, false));
            System.out.println(p.getName() + " has rolled " + dice1 + ", " + dice2);
        }

        return gameDice;
    }

}
