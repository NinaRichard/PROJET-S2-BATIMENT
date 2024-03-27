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
    private double hauteursousplafont;
    private List<Logement> appartementEtage= new ArrayList();

    public Etage(int id, double hauteursousplafont) {
        this.id = id;
        this.hauteursousplafont = hauteursousplafont;
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
        return hauteursousplafont;
    }

    public void setHauteursousplafont(double hauteursousplafont) {
        this.hauteursousplafont = hauteursousplafont;
    }

   public void ajouterAppartement( Logement nouvelAppartement){
        appartementEtage.add(nouvelAppartement);
    }
    
    
}
