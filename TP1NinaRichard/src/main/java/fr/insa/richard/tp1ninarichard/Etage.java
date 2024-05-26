/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.richard.tp1ninarichard;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author inspi
 */
public class Etage {
    protected int id;
    private double hauteursousplafond;
    private int nbrMur;
    private List<Mur> murEtage= new ArrayList();
    
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

    //HauteursousPlafond
    public double getHauteursousplafond() {
        return hauteursousplafond;
    }
    public void setHauteursousplafond(double hauteursousplafond) {
        this.hauteursousplafond = hauteursousplafond;
    }

    // Liste de Mur
    public List<Mur> getMurEtage() {
        return murEtage;
    }
    public void setMurEtage(List<Mur> murEtage) {
        this.murEtage = murEtage;
        nbrMur = murEtage.size();
    }
    
    public void ajouterMur( Mur nouveauMur){
        murEtage.add(nouveauMur);
        nbrMur ++;
    }
}
