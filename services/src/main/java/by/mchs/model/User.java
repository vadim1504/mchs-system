package by.mchs.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "coordinate_x")
    private double coordinateX;
    @Column(name = "coordinate_y")
    private double coordinateY;

    public User(){

    }

    public User(int id,String firstName, String lastName, String number) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public User(int id,String firstName, String lastName, String number, double coordinateX,double coordinateY) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

}
