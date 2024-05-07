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
public class Maison extends Batiment{
    private List<EtageM> batiment= new ArrayList();
    
    //Constructeurs
    public Maison(int id, int nbrEtage) {
        super(id, "Maison", nbrEtage);
    }
    public Maison(int id, String address) {
        super(id, "Maison",address);
    }

    //Getter et Setter
    //List<Etage> batiment
    public List<EtageM> getBatiment() {
        return batiment;
    }
    public void setBatiment(List<EtageM> batiment) {
        this.batiment = batiment;
        this.setNbrEtage(1);// mettre le nbr de truc dans l'autre
    }
    
    //Ajouter un etage à la liste
    public void ajouterEtage( EtageM nouvelEtage){
        batiment.add(nouvelEtage);
        this.setNbrEtage(this.getNbrEtage() + 1);
    }
}
