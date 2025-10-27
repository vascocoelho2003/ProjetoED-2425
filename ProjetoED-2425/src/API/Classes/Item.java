package API.Classes;

import API.Enumeracoes.ItemType;
import API.Interfaces.CharacterADT;
import API.Interfaces.ItemADT;
import API.Interfaces.ToCruzADT;

public class Item implements ItemADT {
    private int recoveredPoints;
    private int extraPoints;
    private ItemType type;
    private int effectValue;


    public int getRecoveredPoints() {
        return recoveredPoints;
    }

    public void setRecoveredPoints(int recoveredPoints) {
        this.recoveredPoints = recoveredPoints;
    }

    public int getExtraPoints() {
        return extraPoints;
    }

    public void setExtraPoints(int extraPoints) {
        this.extraPoints = extraPoints;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Item(ItemType type) {
        this.recoveredPoints=0;
        this.effectValue =  effectValue;
        this.extraPoints=0;
        this.type = type;
    }

    @Override
    public void applyEffect(ToCruzADT toCruz) {
        switch (ItemType.valueOf(type.toString().toUpperCase())) {
            case ItemType.KIT_DE_VIDA:
                applyHealthKit(toCruz);
                break;
            case ItemType.COLETE:
                applyArmor(toCruz);
                break;
            default:
                System.out.println("Tipo de item desconhecido: " + type);
        }
    }

    private void applyHealthKit(ToCruzADT toCruz) {
        int currentHealth = toCruz.getHealth();
        int maxHealth = 100; // Limite máximo de saúde
        int newHealth = Math.min(currentHealth + effectValue, maxHealth); // Garante que não ultrapasse o limite
        toCruz.setHealth(newHealth);
        System.out.println(ItemType.KIT_DE_VIDA + " usado! Saúde recuperada: " + (newHealth - currentHealth));
    }

    private void applyArmor(ToCruzADT toCruz) {
        int currentHealth = toCruz.getHealth();
        int newHealth = currentHealth + effectValue; // Pode ultrapassar o limite
        toCruz.setHealth(newHealth);
        System.out.println(ItemType.COLETE + " usado! Saúde aumentada em: " + effectValue);
    }

    @Override
    public String toString() {
        return  " (" + type + ", Valor: " + effectValue + ")";
    }

}
