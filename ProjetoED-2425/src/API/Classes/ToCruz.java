package API.Classes;

import API.Interfaces.CharacterADT;
import API.Interfaces.ToCruzADT;
import Exceptions.EmptyCollectionException;
import Stuctures.Graphs.Graph;
import Stuctures.Lists.LinkedUnorderedList;
import Stuctures.Queue.LinkedQueue;
import Stuctures.Stacks.ArrayStack;
import Stuctures.Stacks.LinkedStack;
import Stuctures.Stacks.StackADT;

import java.util.Iterator;

import static API.Enumeracoes.ItemType.COLETE;
import static API.Enumeracoes.ItemType.KIT_DE_VIDA;

public class ToCruz extends Character implements ToCruzADT {
    /**
     * The backpack of the ToCruz character
     */
    private StackADT<Item> backpack;

    /**
     * A boolean that indicates if the ToCruz character has reached the target
     */
    private boolean hasReachedTarget;

    /**
     *  Constructor of the ToCruz class
     * @param name
     * @param power
     */
    public ToCruz(String name, int power) {
        super("ToCruz", power);
        this.backpack=new LinkedStack<Item>();
        this.hasReachedTarget=false;
    }


    public Division calculateShortestPath(Building building){
        Division next=null;
        Iterator<Division> it = null;
        try {
            it = building.getMapa().iteratorShortestPath(this.getCurrentPosition(building), building.getAlvo().getDivisao());
            it.next();
                next = it.next();
        } catch (EmptyCollectionException e) {
            throw new RuntimeException(e);
        }
        return next;
    }

    public boolean hasEnemies(Division division){
        return division.getEnemies().size()>0;
    }

    /**
     * Take an Item from the backpack
     * @return
     * @throws EmptyCollectionException
     */
    public Item popBackpack() throws EmptyCollectionException {
        return this.backpack.pop();
    }

    /**
     * Adds an Item in the backpack
     * @param item
     */
    public void pushBackpack(Item item) {
        this.backpack.push(item);
    }

    /**
     * To Cruz uses an Item
     */
    @Override
    public void useItem() throws EmptyCollectionException {
        Item item  = this.backpack.pop();
        if(item.getType().equals(KIT_DE_VIDA)){
            int health = this.getHealth()+item.getRecoveredPoints();
            if(health>100){
                this.setHealth(100);
            }
            this.setHealth(health);
        }else if(item.getType().equals(COLETE)){
            int health = this.getHealth()+item.getExtraPoints();
            this.setHealth(health);
        }
    }

}
