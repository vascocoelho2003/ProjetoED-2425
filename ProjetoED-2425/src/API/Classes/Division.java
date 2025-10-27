package API.Classes;

import API.Interfaces.DivisionADT;
import API.Interfaces.EnemyADT;
import Exceptions.NoSuchElementException;
import Stuctures.Lists.ArrayUnorderedList;
import Stuctures.Lists.LinkedUnorderedList;
import Stuctures.Queue.LinkedQueue;
import Stuctures.Trees.PriorityQueue;

import java.util.Iterator;

public class Division implements DivisionADT {
    private String name;
    private LinkedUnorderedList<Division> connectedDivisions;
    private ArrayUnorderedList<EnemyADT> enemies;
    private LinkedUnorderedList<Item> items;
    private boolean hasTarget;

    public Division(String name) {
        this.name = name;
        this.connectedDivisions = new LinkedUnorderedList<Division>();
        this.enemies = new ArrayUnorderedList<EnemyADT>();
        this.items = new LinkedUnorderedList<Item>();
        this.hasTarget=false;
    }

    public void setEnemies(ArrayUnorderedList<EnemyADT> enemies) {
        this.enemies = enemies;
    }

    public void setItems(LinkedUnorderedList<Item> items) {
        this.items = items;
    }

    public boolean isHasTarget() {
        return hasTarget;
    }

    public boolean hasEnemies(){
        return this.enemies.size()>0;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedUnorderedList<Division> getConnectedDivisions() {
        return connectedDivisions;
    }

    public void setConnectedDivisions(LinkedUnorderedList<Division> connectedDivisions) {
        this.connectedDivisions = connectedDivisions;
    }

    public ArrayUnorderedList<Enemy> getEnemies() {
        return this.getEnemies();
    }

    public void setEnemies(Enemy enemies) throws NoSuchElementException {
        this.enemies.addToFront(enemies);

    }
    public LinkedUnorderedList<Item> getItems() {
        return items;
    }

    public Iterator<Item> getItemsIterator(){
        return this.items.iterator();
    }

    public void setItems(Item item) {
        this.items.addToFront(item);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
