package by.mchs.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE )
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_tower", nullable = false)
    private int idTower;
    @Column(name = "id_user", nullable = false)
    private int idUser;
    @Column(name = "time_call", nullable = false)
    private Time timeCall;
    @Column(name = "distance", nullable = false)
    private double distance;
    @Column(name = "id_call", nullable = false)
    private int idCall;

    public Call(){

    }

    public Call(int idCall, int idTower, int idUser, Time timeCall, double distance) {
        this.idCall = idCall;
        this.idTower = idTower;
        this.idUser = idUser;
        this.timeCall = timeCall;
        this.distance = distance;
    }

    public Call(int idCall,int idUser) {
        this.idCall = idCall;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTower() {
        return idTower;
    }

    public void setIdTower(int idTower) {
        this.idTower = idTower;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Time getTimeCall() {
        return timeCall;
    }

    public void setTimeCall(Time timeCall) {
        this.timeCall = timeCall;
    }

    public int getIdCall() {
        return idCall;
    }

    public void setIdCall(int idCall) {
        this.idCall = idCall;
    }
}
