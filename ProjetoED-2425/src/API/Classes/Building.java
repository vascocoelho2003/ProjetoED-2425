package API.Classes;

import API.Interfaces.BuildingADT;
import Exceptions.EmptyCollectionException;
import Stuctures.Graphs.Graph;
import Stuctures.Lists.ArrayUnorderedList;
import Stuctures.Lists.LinkedUnorderedList;
import Stuctures.Lists.UnorderedListADT;

import java.util.Iterator;

public class Building implements BuildingADT {

    private Graph<Division> mapa;
    private ArrayUnorderedList<Division> entradas;
    private LinkedUnorderedList<Division> divisionsList;
    private static Alvo alvo;

    public Building() {
        this.mapa = new Graph<Division>();
        this.entradas = new ArrayUnorderedList<Division>();
        this.divisionsList=new LinkedUnorderedList<>();
        this.alvo=null;
    }


    public LinkedUnorderedList<Division> getDivisionsList() {
        return divisionsList;
    }
    public void setDivisionsList(LinkedUnorderedList<Division> divisionsList) {
        this.divisionsList = divisionsList;
    }
    public Graph<Division> getMapa() {
        return mapa;
    }

    public void setMapa(Graph<Division> mapa) {
        this.mapa = mapa;
    }

    public UnorderedListADT<Division> getEntradas() {
        return entradas;
    }

    public void setEntradas(ArrayUnorderedList<Division> entradas) {
        this.entradas = entradas;
    }

    public static Alvo getAlvo() {
        return alvo;
    }

    public static void setAlvo(Alvo alvo) {
        Building.alvo = alvo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Informações do alvo
        sb.append("===== Estado do Edifício =====\n");
        sb.append("Alvo: ").append(alvo != null ? alvo : "Não definido").append("\n");

        // Entradas
        sb.append("\nEntradas:\n");
        if (entradas.isEmpty()) {
            sb.append("Nenhuma entrada definida.\n");
        } else {
            for (Division entrada : entradas) {
                sb.append(" - ").append(entrada).append("\n");
            }
        }

        // Exibir o mapa manualmente
        sb.append("\nMapa do Edifício:\n");
        if (mapa.isEmpty()) {
            sb.append("O mapa está vazio.\n");
        } else {
            Division[] vertices = mapa.getVertices();
            int numVertices = mapa.size();

            // Cabeçalho
            sb.append("     ");
            for (Division vertex : vertices) {
                sb.append(String.format("%-10s", vertex));
            }
            sb.append("\n");

            sb.append("  +").append("-".repeat(10 * numVertices)).append("\n");

            // Corpo da matriz (verificando conexões através de BFS)
            for (int i = 0; i < numVertices; i++) {
                sb.append(String.format("%-4s|", vertices[i]));
                for (int j = 0; j < numVertices; j++) {
                    boolean connected = areConnected(vertices[i], vertices[j]);
                    sb.append(String.format("%-10d", connected ? 1 : 0));
                }
                sb.append("\n");
            }
        }

        sb.append("==============================\n");
        return sb.toString();
    }

    private boolean areConnected(Division v1, Division v2) {
        try {
            Iterator<Division> it = mapa.iteratorBFS(v1);
            while (it.hasNext()) {
                if (it.next().equals(v2)) {
                    return true;
                }
            }
        } catch (EmptyCollectionException e) {
            // Se ocorrer uma exceção durante o BFS, pode ser que o vértice não esteja acessível
            return false;
        }
        return false;
    }

    public Division findDivisionByName(UnorderedListADT<Division> divisionsList, String divisionName) {
        for (Division division : divisionsList) {
            if (division.getName().equals(divisionName)) {
                return division;
            }
        }
        return null;
    }
}
