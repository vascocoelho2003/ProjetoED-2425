package API.Interfaces;

public interface CharacterADT {

    void takeDamage(int damage);
    public int getPower();
    public int getHealth();
    void attack(CharacterADT target);
    int getHealth();
    void setHealth(int health);

}
