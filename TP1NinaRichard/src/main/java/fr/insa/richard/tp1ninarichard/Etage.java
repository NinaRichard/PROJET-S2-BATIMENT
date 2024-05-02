/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;


/**
 *
 * @author inspi
 */
public class Etage {
    private int id;
    private double hauteursousplafond;
    
    //Constructeurs
    public Etage(int id) {
        this.id = id;
    }
    public Etage(int id, double hauteursousplafond) {
        this.id = id;
        this.hauteursousplafond = hauteursousplafond;
    }

    //Getter et Setter
    //Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //HauteursousPlafont
    public double getHauteursousplafont() {
        return hauteursousplafond;
    }
    public void setHauteursousplafond(double hauteursousplafond) {
        this.hauteursousplafond = hauteursousplafond;
    }

}
