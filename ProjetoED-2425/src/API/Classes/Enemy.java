package API.Classes;

import API.Interfaces.EnemyADT;

public class Enemy extends Character implements EnemyADT{
    public Enemy(String enemyName, int enemyPower) {
        super(enemyName, enemyPower);
    }
    public void randomMove(Division div){
    }


}
