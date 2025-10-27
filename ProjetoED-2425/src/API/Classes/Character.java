package API.Classes;

import API.Interfaces.CharacterADT;
import Exceptions.EmptyCollectionException;
import Stuctures.Stacks.ArrayStack;

import java.util.Iterator;

public abstract class Character implements CharacterADT {
    private String name;
    private int health;
    private int power;
    private ArrayStack<Division> divisionHistory = new ArrayStack<Division>();

    public Character(String name, int power) {
        this.name = name;
        this.health = 100;
        this.power = power;
    }

    public void takeDamage(int damage) {
        int health = getHealth();
        health -= damage;
    }

    public void attack(CharacterADT target) {
        int power = getPower();
        target.takeDamage(power);
    }


    public void setInitialPosition(Division division){
        this.divisionHistory.push(division);
    }



    public void takeDamage(int damage) {
        int health = getHealth();
        health -= damage;
    }

    public void attack(CharacterADT target) {
        int power = getPower();
        target.takeDamage(power);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void moveAutomatic(Building building, Division target) throws EmptyCollectionException {
        Division destination = building.findDivisionByName(building.getDivisionsList(), target.getName());
        Division start = this.divisionHistory.peek();
        Iterator<Division> pathIterator = building.getMapa().iteratorShortestPath(start, destination);

        while (pathIterator.hasNext()) {
            Division next = pathIterator.next();
            if (!next.equals(start)) {
                moveCharacter(start, next);
                start = next;

            }
        }
    }

    private void handleTargetReached(Division current) throws EmptyCollectionException {
        if (current.hasEnemies()) {
            System.out.println(this.getName() + " has reached the target but there are enemies in the division");
        } else {
            current.setHasTarget(false);
            moveCharacter(this.divisionHistory.peek(),current);
        }
    }

    protected Division getCurrentPosition(Building building) throws EmptyCollectionException {
        return building.findDivisionByName(building.getDivisionsList(), this.divisionHistory.peek().getName());
    }

    protected void moveCharacter(Division from, Division to) {
        System.out.println(this.getName() + " is moving from " + from.getName() + " to " + to.getName());
        this.divisionHistory.push(to);
    }

    private boolean checkIfReachedTarget(Division current, Division target) {
        return current.equals(target);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
