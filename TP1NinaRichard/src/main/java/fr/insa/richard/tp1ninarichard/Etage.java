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
    private int id;
    private double hauteursousplafond;
    private int nbrdappart;
    private List<Logement> appartementEtage= new ArrayList();

    public Etage(int id, double hauteursousplafond) {
        this.id = id;
        this.hauteursousplafond = hauteursousplafond;
    }

    public List<Logement> getAppartementEtage() {
        return appartementEtage;
    }

    public void setAppartementetage(List<Logement> appartementetage) {
        this.appartementEtage = appartementetage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHauteursousplafont() {
        return hauteursousplafond;
    }

    public void setHauteursousplafond(double hauteursousplafond) {
        this.hauteursousplafond = hauteursousplafond;
    }

   public void ajouterAppartement( Logement nouvelAppartement){
        appartementEtage.add(nouvelAppartement);
        nbrdappart ++;
    }
    
    
}
