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
public class Batiment {
    private int id;
    private String type;
    private int nbrEtage;
    private List<Etage> batiment= new ArrayList();

    public Batiment(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<Etage> getBatiment() {
        return batiment;
    }

    public void setBatiment(List<Etage> batiment) {
        this.batiment = batiment;
        nbrEtage = 1;// mettre le nbr de truc dans l'autre
    }
    
     public void ajouterEtage( Etage nouvelEtage){
        batiment.add(nouvelEtage);
        this.nbrEtage ++;
    }
    
}
