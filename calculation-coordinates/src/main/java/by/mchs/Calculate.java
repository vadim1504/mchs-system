package by.mchs;

import by.mchs.model.Call;

import java.awt.*;
import java.util.ArrayList;

public class Calculate {

    private static final double side = 10000;

    public static Point getCoordinate(ArrayList<Call> c) {
        ArrayList<Call> calls = new ArrayList<>(c);
        Point coordinate = new Point();
        double min = calls.get(0).getDistance();
        int i = 0;
        int id_min = 0;
        int idTowerMin = 0;
        while (i < calls.size()) {
            if (calls.get(i).getDistance() <= min) {
                min = calls.get(i).getDistance();
                id_min = i;
                idTowerMin = calls.get(i).getIdTower();
            }
            i++;
        }
        calls.remove(id_min);
        double[] medians = new double[2];
        for (i = 0; i < calls.size(); i++) {
            medians[i] = Math.sqrt(min * calls.get(i).getDistance() * (min + calls.get(i).getDistance() + side) * (min + calls.get(i).getDistance() - side)) / (min + calls.get(i).getDistance());
        }
        switch (idTowerMin) {
            case 1:
                coordinate.setLocation(medians[0],medians[1]);
                break;
            case 2:
                coordinate.setLocation(medians[0],side-medians[1]);
                break;
            case 3:
                coordinate.setLocation(side-medians[0],side-medians[1]);
                break;
            case 4:
                coordinate.setLocation(side-medians[0],medians[1]);
                break;
        }
        return coordinate;
    }
}
