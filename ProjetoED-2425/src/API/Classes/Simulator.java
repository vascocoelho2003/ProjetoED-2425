package API.Classes;

import API.Enumeracoes.AlvoType;
import API.Enumeracoes.ItemType;
import API.Interfaces.CharacterADT;
import API.Interfaces.SimulatorADT;
import Exceptions.EmptyCollectionException;
import Stuctures.Lists.ArrayOrderedList;
import Stuctures.Lists.LinkedUnorderedList;
import Stuctures.Lists.UnorderedListADT;
import Stuctures.Queue.LinkedQueue;
import Stuctures.Stacks.LinkedStack;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.util.Scanner;

public class Simulator implements SimulatorADT {
    private String jsonFile;
    private Building building;
    private int numberOfDivisions;
    private ToCruz toCruz;
    private Mission mission;
    private boolean isAutomatic;
    private int turns;
    private LinkedStack actions;

    public Simulator(String jsonFile) {
        this.jsonFile=jsonFile;
        this.building=new Building();
        this.toCruz=null;
        this.mission=null;
        this.isAutomatic=false;
        this.turns=0;
        this.actions=null;
    }


    public void importData() {
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(jsonFile)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            String codmissao = String.valueOf(jsonObject.get("cod-missao"));
            int versao = Integer.valueOf(String.valueOf(jsonObject.get("versao")));
            this.mission  = new Mission(codmissao, versao);

            JsonArray edificiosJson = jsonObject.getAsJsonArray("edificio");
            UnorderedListADT<Division> divisionsList = new LinkedUnorderedList<>();

            for (JsonElement element : edificiosJson) {
                String divisionName = element.getAsString();
                Division division = new Division(divisionName);
                divisionsList.addToFront(division);
                building.getMapa().addVertex(division);
                this.building.getDivisionsList().addToFront(division);
                numberOfDivisions++;
            }

            JsonArray ligacoesJson = jsonObject.getAsJsonArray("ligacoes");
            for (JsonElement element : ligacoesJson) {
                JsonArray connection = element.getAsJsonArray();
                String division1 = connection.get(0).getAsString();
                String division2 = connection.get(1).getAsString();

                Division div1 = building.findDivisionByName(divisionsList, division1);
                Division div2 = building.findDivisionByName(divisionsList, division2);

                if (div1 != null && div2 != null) {
                    building.getMapa().addEdge(div1, div2);

                    div1.getConnectedDivisions().addToFront(div2);
                    div2.getConnectedDivisions().addToFront(div1);


                }
            }

            JsonArray enemies = jsonObject.getAsJsonArray("inimigos");
            for (JsonElement element : enemies) {
                JsonObject enemy = element.getAsJsonObject();
                String enemyName = enemy.get("nome").getAsString();
                int enemyPower = enemy.get("poder").getAsInt();
                Division enemyDivision = building.findDivisionByName(divisionsList, enemy.get("divisao").getAsString());
                Enemy enemyObj = new Enemy(enemyName, enemyPower);
                enemyDivision.setEnemies(enemyObj);
            }

            JsonArray entradassaidas = jsonObject.getAsJsonArray("entradas-saidas");
            for (JsonElement element : entradassaidas) {
                String divisionName = element.getAsString();
                building.getEntradas().addToFront(building.findDivisionByName(divisionsList, divisionName));
            }

            JsonObject alvo = jsonObject.getAsJsonObject("alvo");
            Division targetDivision = building.findDivisionByName(divisionsList,alvo.get("divisao").getAsString());
            AlvoType type = AlvoType.valueOf(alvo.get("tipo").getAsString().toUpperCase());


            targetDivision.setHasTarget(true);
            Alvo target = new Alvo(targetDivision, type);
            mission.setTarget(target);
            building.setAlvo(target);

            mission.setBuilding(building);

            JsonArray itens = jsonObject.getAsJsonArray("itens");
            for (JsonElement element : itens) {
                JsonObject item = element.getAsJsonObject();
                Division division = building.findDivisionByName(divisionsList,item.get("divisao").getAsString());

                String tipoString = item.get("tipo").getAsString().toUpperCase().replace(" ", "_");

                        ItemType itemType = ItemType.valueOf(tipoString);
                        Item itemObj = new Item(itemType);
                        division.setItems(itemObj);

                        int recoveredPoints = item.has("pontos-recuperados") ? item.get("pontos-recuperados").getAsInt() : 0;
                        int extraPoints = item.has("pontos-extra") ? item.get("pontos-extra").getAsInt() : 0;

                        itemObj.setRecoveredPoints(recoveredPoints);
                        itemObj.setExtraPoints(extraPoints);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runAutomatic(){}

    public void manualSimulation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao modo manual!");

        while (true) {
            System.out.println("Escolha a próxima divisão:");
            String nextDivision = scanner.nextLine();
            Division targetDivision = findDivisionByName(building.getEntradas(), nextDivision);

            if (targetDivision != null) {
                toCruz.moveTo(targetDivision);
                if (!targetDivision.getEnemies().isEmpty()) {
                    combat(targetDivision);
                }
            } else {
                System.out.println("Divisão inválida!");
            }

            if (toCruz.isHasReachedTarget()) {
                System.out.println("Missão concluída com sucesso!");
                break;
            }
        }
    }

    public String getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ToCruz getToCruz() {
        return toCruz;
    }

    public void setToCruz(ToCruz toCruz) {
        this.toCruz = toCruz;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public LinkedStack getActions() {
        return actions;
    }

    public void setActions(LinkedStack actions) {
        this.actions = actions;
    }

    public int getNumberOfDivisions() {
        return numberOfDivisions;
    }

    @Override
    public void takeDamage(int damage) {
        toCruz.takeDamage(damage);
    }

    @Override
    public void attack(CharacterADT target) {
        toCruz.attack(target);
    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void setHealth(int health) {

    }

    public void combat(Division division) {
        ArrayOrderedList<Enemy> enemies = division.getEnemies();

        System.out.println("Combate iniciado na divisão: " + division.getName());
        while (!enemies.isEmpty()) {
            Enemy enemy;
            try {
                enemy = enemies.removeFirst(); // Remove o inimigo com menor poder
            } catch (EmptyCollectionException e) {
                System.out.println("Erro ao remover inimigo: " + e.getMessage());
                return;
            }

            // Tó Cruz ataca o inimigo
            toCruz.attack(enemy);
            System.out.println(toCruz.getName() + " atacou " + enemy.getName());

            // Se o inimigo ainda estiver vivo, ele ataca o Tó Cruz
            if (enemy.getHealth() > 0) {
                enemy.attack(toCruz);
                System.out.println(enemy.getName() + " atacou " + toCruz.getName());
            }

            // Verificar se o Tó Cruz morreu
            if (toCruz.getHealth() <= 0) {
                System.out.println("Tó Cruz morreu. Missão falhou!");
                return;
            }
        }

        System.out.println("Todos os inimigos eliminados!");
    }

}
