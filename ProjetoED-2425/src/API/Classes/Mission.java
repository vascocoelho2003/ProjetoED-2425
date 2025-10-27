package API.Classes;

import API.Interfaces.MissionADT;
import Stuctures.Lists.LinkedUnorderedList;
import Stuctures.Queue.LinkedQueue;

public class Mission implements MissionADT {
    private String codMission;
    private int version;
    private Building building;
    private Alvo target;

    public Mission(String codMission, int version) {
        this.codMission = codMission;
        this.version = version;

    }

    public String getCodMission() {
        return codMission;
    }

    public void setCodMission(String codMission) {
        this.codMission = codMission;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Alvo getTarget() {
        return target;
    }

    public void setTarget(Alvo target) {
        this.target = target;
    }
}
