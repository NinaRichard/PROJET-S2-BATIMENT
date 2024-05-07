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
public class Immeuble extends Batiment{
    private List<EtageI> batiment= new ArrayList();
    
    //Constructeur
    public Immeuble(int id,String adresse) {
        super(id, "Immeuble", adresse);
    }
    
    //Getter et Setter
    //List<Etage> batiment
    public List<EtageI> getBatiment() {
        return batiment;
    }
    public void setBatiment(List<EtageI> batiment) {
        this.batiment = batiment;
        this.setNbrEtage(1);// mettre le nbr de truc dans l'autre
    }
    
    //Ajouter un etage à la liste
    public void ajouterEtage( EtageI nouvelEtage){
        batiment.add(nouvelEtage);
        this.setNbrEtage(this.getNbrEtage() + 1);
    }
    
}
