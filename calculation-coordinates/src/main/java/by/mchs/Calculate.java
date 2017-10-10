package by.mchs;

import by.mchs.model.Call;

import java.awt.*;
import java.util.ArrayList;

public class Calculate {

    private static final double side = 10000;

    /** The method calculates the coordinate points of the user to the distance to the towers
     * The towers are located in the tops of a square with a size of 10000*10000
     * The method will return Point(x=NaN,y=NaN) if the input parameters are not valid or such a point with such parameters does not exist
     * @param calls a set of registered calls on towers from this user, at least 2 calls
     * @return object the Point containing the coordinate points
     */
    public static Point getCoordinate(ArrayList<Call> calls) {
        ArrayList<Call> callscopy = new ArrayList<>(calls);
        Point coordinate = new Point();
        double min = callscopy.get(0).getDistance();
        int i = 0;
        int id_min = 0;
        int idTowerMin = 0;
        while (i < callscopy.size()) {
            if (callscopy.get(i).getDistance() <= min) {
                min = callscopy.get(i).getDistance();
                id_min = i;
                idTowerMin = callscopy.get(i).getIdTower();
            }
            i++;
        }
        callscopy.remove(id_min);
        double[] medians = new double[2];
        for (i = 0; i < callscopy.size(); i++) {
            medians[i] = Math.sqrt(min * callscopy.get(i).getDistance() * (min + callscopy.get(i).getDistance() + side) * (min + callscopy.get(i).getDistance() - side)) / (min + callscopy.get(i).getDistance());
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
