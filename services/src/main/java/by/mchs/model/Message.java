package by.mchs.model;

import java.sql.Time;

public class Message {

    private int idTower;
    private int idSub;
    private String firstName;
    private String lastName;
    private String number;
    private int idCall;
    private Time timeCall;
    private double distance;

    public Message(){

    }

    public Message(int idTower, int idSub, String number, int idCall, Time timeCall, double distance) {
        this.idTower = idTower;
        this.idSub = idSub;
        this.number = number;
        this.idCall = idCall;
        this.timeCall = timeCall;
        this.distance = distance;
    }

    public Message(int idTower, int idSub, String firstName, String lastName, String number, int idCall, Time timeCall, double distance) {
        this.idTower = idTower;
        this.idSub = idSub;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.idCall = idCall;
        this.timeCall = timeCall;
        this.distance = distance;
    }

    public int getIdTower() {
        return idTower;
    }

    public void setIdTower(int idTower) {
        this.idTower = idTower;
    }

    public int getIdSub() {
        return idSub;
    }

    public void setIdSub(int idSub) {
        this.idSub = idSub;
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

    public int getIdCall() {
        return idCall;
    }

    public void setIdCall(int idCall) {
        this.idCall = idCall;
    }

    public Time getTimeCall() {
        return timeCall;
    }

    public void setTimeCall(Time timeCall) {
        this.timeCall = timeCall;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", idTower=" + idTower +
                ", idSub=" + idSub +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + number + '\'' +
                ", idCall=" + idCall +
                ", timeCall=" + timeCall +
                ", distance=" + distance +
                '}';
    }
}
