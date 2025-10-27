package API.Classes;

import API.Enumeracoes.AlvoType;
import API.Interfaces.AlvoADT;

public class Alvo implements AlvoADT {
    private Division name;
    private AlvoType type;
    private boolean isHit;

    public Alvo(Division divisao, AlvoType type) {
        this.name = divisao;
        this.type = type;
        this.isHit=false;
    }

    public Division getName() {
        return name;
    }

    public void setName(Division name) {
        this.name = name;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public Division getDivisao() {
        return name;
    }

    public void setDivisao(Division divisao) {
        this.name = divisao;
    }

    public AlvoType getType() {
        return type;
    }

    public void setType(AlvoType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name.toString();
    }
}
